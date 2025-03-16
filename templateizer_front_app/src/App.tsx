import { BrowserRouter as Router, Navigate, Route, Routes } from 'react-router';
import styles from './App.module.css';
import Header from './components/organism/Header.tsx';
import Home from './components/pages/Home.tsx';
import { AuthProvider } from './keycloak/AuthContext.tsx';
import ProtectedRoute from './keycloak/ProtectedRoute.tsx';
import PermissionGuard from './keycloak/PermissionGuard.tsx';

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <div className={styles.wrapper}>
          <Header />
          <main className={styles.main}>
            <Routes>
              <Route element={<ProtectedRoute />}>
                <Route path="/" element={<Navigate to="/home" />} />
                <Route path="/home" element={<Home />} />
                <Route
                  path="/admin"
                  element={
                    <PermissionGuard permissions={['templateizer_admin']}>
                      <div>admin panel</div>
                    </PermissionGuard>
                  }
                />
              </Route>
              <Route path="/logout" element={<div>Разлогинились</div>} />
            </Routes>
          </main>
          <footer>footer</footer>
        </div>
      </Router>
    </AuthProvider>
  );
};

export default App;
