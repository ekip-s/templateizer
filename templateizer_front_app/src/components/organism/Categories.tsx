import styles from '../pages/AdminPage.module.css';
//import useApi from '../../api/useApi.tsx';
import AddNewCategories from '../molecules/AddNewCategories.tsx';

const Categories = () => {
  //const { data, error } = useApi({ url: '/categories/api/v1/list' });

  return (
    <section className={`${styles.categoryManagement} ${styles.adminNode}`}>
      <h4>Управление категориями</h4>
      <AddNewCategories />
    </section>
  );
};

export default Categories;
