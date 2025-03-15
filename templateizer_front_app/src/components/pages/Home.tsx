import SearchSection from '../organism/SearchSection.tsx';
import styles from './Home.module.css';

const Home = () => {
  return (
    <>
      <div className={styles.selectWrapper}>
        <SearchSection />
      </div>
    </>
  );
};

export default Home;
