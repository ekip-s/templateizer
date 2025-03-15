import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router";
import styles from "./App.module.css";
import Header from "./components/organism/Header.tsx";
import Home from "./components/pages/Home.tsx";

const App = () => {
  return (
    <>
      <Router>
        <div className={styles.wrapper}>
          <Header />
          <main className={styles.main}>
            <Routes>
              <Route path="/" element={<Navigate to="/home" />} />
              <Route path="/home" element={<Home />} />
            </Routes>
          </main>
          <footer>footer</footer>
        </div>
      </Router>
    </>
  );
};

export default App;
