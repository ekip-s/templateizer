import styles from './AddNewCategories.module.css';
import { InputText } from 'primereact/inputtext';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';
import { useState } from 'react';
import { send } from '../../api/sendHTTP.tsx';
import { useAuth } from '../../keycloak/AuthContext.tsx';
import Category from '../../model/CategoryModel.tsx';

interface AddNewCategoriesType {
  data: Category[];
  setData: (data: Category[] | ((prevData: Category[]) => Category[])) => void;
}

const AddNewCategories = ({ data, setData }: AddNewCategoriesType) => {
  const [immutable, setImmutable] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState('');
  const [categoryName, setCategoryName] = useState<string>('');
  const { getToken } = useAuth();

  const AddNewCategoriesHandler = () => {
    const isCategoryExist = data.some(
      (item) => item.name.toLowerCase() === categoryName.toLowerCase().trim()
    );

    if (isCategoryExist) {
      setError('Категория с таким именем уже существует!');
      return;
    }

    const addCategoryByList = (category: Category) => {
      setData((prevData) => [category, ...prevData]);
    };

    send<Category>({
      url: '/categories/api/v1',
      method: 'POST',
      body: {
        name: categoryName,
        immutable: immutable,
      },
      token: getToken(),
      setLoading: setLoading,
      setError: setError,
      setDataInfo: addCategoryByList,
    });
  };

  return (
    <section>
      <h5>Новая категория</h5>
      <div className={styles.addNewCategories}>
        <InputText
          className={styles.categoryInput}
          placeholder="Название новой категории"
          value={categoryName}
          onChange={(e) => setCategoryName(e.target.value)}
        />
        <div className={styles.checkboxBox}>
          <Checkbox
            inputId="immutable"
            checked={immutable}
            onChange={(e) => setImmutable(e.checked ?? false)}
          />
          <label htmlFor="immutable">Неизменяемая</label>
        </div>
        <Button
          label="Добавить категорию"
          className={styles.categoryBtn}
          disabled={categoryName.trim().length < 3}
          onClick={AddNewCategoriesHandler}
          loading={loading}
        />
      </div>
      {error.trim() && (
        <div className={styles.btnError}>
          <div>{error}</div>
        </div>
      )}
    </section>
  );
};

export default AddNewCategories;
