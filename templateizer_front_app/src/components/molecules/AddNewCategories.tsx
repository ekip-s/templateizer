import styles from './AddNewCategories.module.css';
import { InputText } from 'primereact/inputtext';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';

const AddNewCategories = () => {
  return (
    <section className={styles.addNewCategories}>
      <InputText />
      <Checkbox checked={false} />
      <Button label="Добавить категорию" />
    </section>
  );
};

export default AddNewCategories;
