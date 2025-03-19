import React, {
  useEffect,
  useMemo,
  useState,
  useRef,
  useCallback,
} from 'react';
import { useAuth } from '../keycloak/AuthContext.tsx';

type HttpMethod = 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE';

interface ApiConfig {
  url: string;
  method?: HttpMethod;
  body?: object | null;
  headers?: Record<string, string>;
}

interface UseApiResponse<T> {
  data: T | null;
  loading: boolean;
  error: Error | null;
  setData: React.Dispatch<React.SetStateAction<T | null>>;
  refresh: () => Promise<void>;
}

const useApi = <T = unknown,>({
  url,
  method = 'GET',
  body = null,
  headers = {},
}: ApiConfig): UseApiResponse<T> => {
  const { getToken } = useAuth();
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [shouldFetch, setShouldFetch] = useState(true);
  const isMounted = useRef(false);
  const abortControllerRef = useRef(new AbortController());

  useEffect(() => {
    isMounted.current = true;
    return () => {
      isMounted.current = false;
      abortControllerRef.current.abort();
    };
  }, []);

  const memoizedBody = useMemo(
    () => (body ? JSON.stringify(body) : null),
    [body]
  );

  const getAuthHeader = useCallback(() => {
    const token = getToken();
    return token ? { Authorization: `Bearer ${token}` } : {};
  }, [getToken]);

  const memoizedHeaders = useMemo(() => {
    return Object.fromEntries(
      Object.entries({
        'Content-Type': 'application/json',
        ...headers,
        ...getAuthHeader(),
      }).filter(([, v]) => v !== undefined)
    ) as Record<string, string>;
  }, [headers, getAuthHeader]);

  const fetchData = useCallback(async () => {
    if (!isMounted.current || !shouldFetch) return;

    abortControllerRef.current = new AbortController();

    try {
      setLoading(true);
      setError(null);

      const response = await fetch(
        `${import.meta.env.VITE_BACKEND_URL}${url}`,
        {
          method,
          headers: memoizedHeaders,
          body: memoizedBody,
          signal: abortControllerRef.current.signal,
        }
      );

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const result = (await response.json()) as T;
      if (isMounted.current) {
        setData(result);
        setShouldFetch(false); // Блокируем повторные запросы после успеха
      }
    } catch (err) {
      if (isMounted.current && !abortControllerRef.current.signal.aborted) {
        setError(
          err instanceof Error ? err : new Error('An unknown error occurred')
        );
        setShouldFetch(false); // Блокируем автоматические повторные запросы
      }
    } finally {
      if (isMounted.current) {
        setLoading(false);
      }
    }
  }, [url, method, memoizedBody, memoizedHeaders, shouldFetch]);

  const refresh = useCallback(async () => {
    abortControllerRef.current.abort();
    setShouldFetch(true); // Разблокируем запросы при ручном обновлении
    setData(null);
    setError(null);
  }, []);

  useEffect(() => {
    if (shouldFetch) {
      fetchData();
    }
  }, [shouldFetch, fetchData]);

  return { data, loading, error, setData, refresh };
};

export default useApi;
