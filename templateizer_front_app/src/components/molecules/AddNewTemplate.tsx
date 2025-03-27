import styles from './AddNewTemplate.module.css';
import admin from '../pages/AdminPage.module.css';
import { InputTextarea } from 'primereact/inputtextarea';
import { Button } from 'primereact/button';
import Select from 'react-select';
import { InputText } from 'primereact/inputtext';
import useApi from '../../api/useApi.tsx';
import Category from '../../model/CategoryModel.tsx';
import Loading from '../atoms/Loading.tsx';
import Error from '../atoms/Error.tsx';
import React, { useState } from 'react';
import { send } from '../../api/sendHTTP.tsx';
import { useAuth } from '../../keycloak/AuthContext.tsx';

interface SelectOption {
  value: string;
  label: string;
}

const AddNewTemplate = ({ isAdmin = false }: { isAdmin?: boolean }) => {
  const [selectedOption, setSelectedOption] = useState<SelectOption | null>(
    null
  );
  const [description, setDescription] = useState('');
  const [title, setTitle] = useState('');
  const { getToken } = useAuth();

  const { data, loading, error } = useApi<Category[] | null>({
    url: '/categories/api/v1/list',
  });

  const options: SelectOption[] =
    data?.map((item) => ({
      value: item.id,
      label: item.name,
    })) || [];

  if (loading) return <Loading />;
  if (error) return <Error message={error.message} />;

  const addNewTemplateHandler = (event: React.SyntheticEvent) => {
    event.preventDefault();

    send({
      url: isAdmin ? '/template/api/v1/admin' : '/template/api/v1',
      method: 'POST',
      body: {
        name: title,
        content: description,
        categoryId: selectedOption?.value,
      },
      token: getToken(),
    });
  };

  return (
    <div className={admin.adminNode}>
      <form onSubmit={addNewTemplateHandler}>
        <h3>Добавить новый шаблон</h3>
        <div className={styles.topMenu}>
          <div>
            <InputText
              className={styles.title}
              placeholder="Заголовок"
              onChange={(e) => setTitle(e.target.value)}
              required={true}
            />
          </div>
          <div>
            <Select
              className={styles.select}
              options={options}
              placeholder="Выбери категорию"
              onChange={(newValue) => setSelectedOption(newValue)}
              required={true}
            />
          </div>
        </div>
        <InputTextarea
          id="description"
          className={styles.inputTextarea}
          placeholder="Вводи текст шаблона..."
          onChange={(e) => setDescription(e.target.value)}
          required={true}
        />
        <div className={styles.btnWrapper}>
          <Button label="Добавить шаблон" className={styles.addTemplateBtn} />
        </div>
      </form>
    </div>
  );
};

export default AddNewTemplate;
