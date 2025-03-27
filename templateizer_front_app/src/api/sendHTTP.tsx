type RequestOptions<T = unknown> = {
  url: string;
  method: 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH';
  body?: unknown;
  token?: string | null;
  headers?: HeadersInit;
  setDataInfo?: (data: T) => void;
  dataType?: 'json' | 'empty' | 'string';
  setLoading?: (loading: boolean) => void;
  setError?: (error: string | '') => void;
};

const send = async <T = unknown,>({
  url,
  method,
  body,
  token,
  headers = {},
  setDataInfo,
  dataType = 'json',
  setLoading,
  setError,
}: RequestOptions<T>): Promise<void> => {
  if (setLoading) setLoading(true);
  if (setError) setError('');

  const options: RequestInit = {
    method,
    headers: {
      'Content-Type': 'application/json',
      ...headers,
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
    },
    body: body ? JSON.stringify(body) : null,
  };

  try {
    const response = await fetch(
      `${import.meta.env.VITE_BACKEND_URL}${url}`,
      options
    );

    if (!response.ok) {
      throw new Error(`Ошибка HTTP: ${response.status}`);
    }

    let result: T | string | null;

    if (dataType === 'string') {
      result = await response.text();
    } else if (dataType === 'empty') {
      result = null;
    } else {
      result = (await response.json()) as T;
    }

    if (setDataInfo) {
      setDataInfo(result as T);
    }
  } catch (err) {
    const errorMessage = (err as Error).message || 'Ошибка выполнения запроса';
    console.error(errorMessage);
    if (setError) setError(errorMessage);
  } finally {
    if (setLoading) setLoading(false);
  }
};

export { send };
