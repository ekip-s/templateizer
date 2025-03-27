import styles from '../pages/AddTemplatePage.module.css';
import AddNewTemplate from '../molecules/AddNewTemplate.tsx';

const AddAdminTemplate = () => {
  return (
    <>
      <div className={styles.addTemplatePage}>
        <AddNewTemplate isAdmin={true} />
      </div>
    </>
  );
};

export default AddAdminTemplate;
