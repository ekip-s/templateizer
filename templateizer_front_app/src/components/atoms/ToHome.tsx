import styles from './ToHome.module.css';
import { useNavigate } from 'react-router';

const ToHome = () => {
  const navigate = useNavigate();

  return (
    <div className={styles.toHome} onClick={() => navigate('/home')}>
      <div>« вернуться на главную</div>
    </div>
  );
};

export default ToHome;
