import styles from './CategoryNode.module.css';
import Category from '../../model/CategoryModel.tsx';
import { Button } from 'primereact/button';
import { send } from '../../api/sendHTTP.tsx';
import { useAuth } from '../../keycloak/AuthContext.tsx';

interface CategoryNodeProps {
  category: Category;
  setData: (data: Category[] | ((prevData: Category[]) => Category[])) => void;
}

const CategoryNode = ({ category, setData }: CategoryNodeProps) => {
  const { id, name, immutable } = category;
  const { getToken } = useAuth();

  const deleteNodeToList = () => {
    setData((prevData) => prevData.filter((item) => item.id !== id));
  };

  const deleteCategoryHandler = () => {
    send({
      url: `/categories/api/v1/${id}`,
      method: 'DELETE',
      token: getToken(),
      dataType: 'empty',
      setDataInfo: deleteNodeToList,
    });
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
