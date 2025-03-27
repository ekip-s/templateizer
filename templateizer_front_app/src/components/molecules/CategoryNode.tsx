import styles from './CategoryNode.module.css';
import Category from '../../model/CategoryModel.tsx';
import { Button } from 'primereact/button';
import { send } from '../../api/sendHTTP.tsx';
import { useAuth } from '../../keycloak/AuthContext.tsx';

interface CategoryNodeProps {
  category: Category;
  refresh: () => void;
}

const CategoryNode = ({ category, refresh }: CategoryNodeProps) => {
  const { id, name, immutable } = category;
  const { getToken } = useAuth();

  const deleteCategoryHandler = () => {
    send({
      url: `/categories/api/v1/${id}`,
      method: 'DELETE',
      token: getToken(),
    });
    refresh();
  };

  return (
    <article className={styles.categoryNode}>
      <div className={styles.categoryName}>
        <label>Категория:</label>
        <div>{name}</div>
      </div>
      {!immutable && (
        <Button
          label="Удалить"
          className={styles.deleteBtn}
          onClick={deleteCategoryHandler}
        />
      )}
    </article>
  );
};

export default CategoryNode;
