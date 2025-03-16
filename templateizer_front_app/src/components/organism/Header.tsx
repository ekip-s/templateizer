import styles from './Header.module.css';
import Profile from '../molecules/Profile.tsx';

const Header = () => {
  const companyName = import.meta.env.VITE_COMPANY_NAME;

  return (
    <header className={styles.header}>
      <div className={styles.wrapper}>
        <div className={styles.logoWrapper}>
          <div className={styles.company}>{companyName}</div>
          <div className={styles.logo}>Шаблонайзер</div>
        </div>
        <div className={styles.profile}>
          <Profile />
        </div>
      </div>
    </header>
  );
};

export default Header;
