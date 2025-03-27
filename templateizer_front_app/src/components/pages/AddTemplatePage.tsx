import styles from './AddTemplatePage.module.css';
import ToHome from '../atoms/ToHome.tsx';
import AddNewTemplate from '../molecules/AddNewTemplate.tsx';

const AddTemplatePage = () => {
  return (
    <>
      <div className={styles.addTemplatePage}>
        <ToHome />
        <AddNewTemplate />
      </div>
    </>
  );
};

export default AddTemplatePage;
