import styles from './BtnSelector.module.css';

interface BtnSelectorProps {
  activeBtn: string;
  setActiveBtn: (btn: string) => void;
  btns: string[];
}

const BtnSelector = ({ activeBtn, setActiveBtn, btns }: BtnSelectorProps) => {
  return (
    <div>
      {btns.map((btnName, i) => (
        <button
          key={`btn_${i}`}
          onClick={() => {
            setActiveBtn(btnName);
          }}
          className={`${styles.btnNode} 
          ${btnName === activeBtn ? styles.active : styles.notActive}`}
        >
          {btnName}
        </button>
      ))}
    </div>
  );
};

export default BtnSelector;
