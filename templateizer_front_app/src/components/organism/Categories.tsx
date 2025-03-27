import styles from '../pages/AdminPage.module.css';
import localStyles from './Categories.module.css';
import useApi from '../../api/useApi.tsx';
import AddNewCategories from '../molecules/AddNewCategories.tsx';
import CategoriesList from '../templates/CategoriesList.tsx';
import Category from '../../model/CategoryModel.tsx';
import Loading from '../atoms/Loading.tsx';
import Error from '../atoms/Error.tsx';

const Categories = () => {
  const { data, error, refresh, loading } = useApi<Category[]>({
    url: '/categories/api/v1/list',
  });

  if (loading) return <Loading />;

  if (error) return <Error message={error.message} />;

  if (!data) {
    return <div>No categories found</div>;
  }

  return (
    <div className={localStyles.categories}>
      <section className={`${styles.categoryManagement} ${styles.adminNode}`}>
        <h4>Управление категориями</h4>
        <AddNewCategories refresh={refresh} data={data} />
        <CategoriesList refresh={refresh} data={data} />
      </section>
    </div>
  );
};

export default Categories;
