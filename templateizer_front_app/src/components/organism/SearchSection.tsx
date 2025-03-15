import styles from './SearchSection.module.css';
import { Checkbox } from 'primereact/checkbox';
import Select from 'react-select';

const SearchSection = () => {
  return (
    <section className={styles.searchSection}>
      <Input />
      <CheckboxWrapper />
      <div className={styles.reactSelect}>
        <Select />
      </div>
    </section>
  );
};

const Input = () => {
  return (
    <div className={styles.input}>
      <input type="text" placeholder="Поиск ..." className={styles.inputText} />
    </div>
  );
};

const CheckboxWrapper = () => {
  return (
    <div className={styles.checkboxWrapper}>
      <span>Только избранное:</span>
      <Checkbox checked={true} />
    </div>
  );
};

export default SearchSection;
