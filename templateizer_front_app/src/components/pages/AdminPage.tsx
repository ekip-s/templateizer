import styles from './AdminPage.module.css';
import BtnSelector from '../atoms/BtnSelector.tsx';
import { useState } from 'react';
import Categories from '../organism/Categories.tsx';
import AddAdminTemplate from '../organism/AddAdminTemplate.tsx';
import ToHome from '../atoms/ToHome.tsx';

const AdminPage = () => {
  const [activeBtn, setActiveBtn] = useState('Создать шаблон');

  return (
    <>
      <div className={styles.topSelector}>
        <ToHome />
        <BtnSelector
          activeBtn={activeBtn}
          setActiveBtn={setActiveBtn}
          btns={['Создать шаблон', 'Категории']}
        />
      </div>
      {activeBtn === 'Категории' ? <Categories /> : <AddAdminTemplate />}
    </>
  );
};

export default AdminPage;
