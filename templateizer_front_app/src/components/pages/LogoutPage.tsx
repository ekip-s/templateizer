import { useAuth } from '../../keycloak/AuthContext.tsx';
import { useNavigate } from 'react-router';
import styles from './LogoutPage.module.css';
import { useEffect } from 'react';

const LogoutPage = () => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    navigate('/home');
  }, [isAuthenticated]);

  return (
    <div className={styles.logoutPage}>
      <div>Успешный выход из учетной записи</div>
    </div>
  );
};

export default LogoutPage;
