import styles from './CategoriesList.module.css';
import Category from '../../model/CategoryModel.tsx';
import CategoryNode from '../molecules/CategoryNode.tsx';

interface CategoriesListType {
  data: Category[];
  refresh: () => void;
}

const CategoriesList = ({ data, refresh }: CategoriesListType) => {
  return (
    <section className={styles.categoriesList}>
      <h5>Список категорий</h5>
      {data.map((category: Category) => (
        <CategoryNode key={category.id} category={category} refresh={refresh} />
      ))}
    </section>
  );
};

export default CategoriesList;
