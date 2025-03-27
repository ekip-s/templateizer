import { BrowserRouter as Router, Navigate, Route, Routes } from 'react-router';
import styles from './App.module.css';
import Header from './components/organism/Header.tsx';
import Home from './components/pages/Home.tsx';
import { AuthProvider } from './keycloak/AuthContext.tsx';
import ProtectedRoute from './keycloak/ProtectedRoute.tsx';
import PermissionGuard from './keycloak/PermissionGuard.tsx';
import LogoutPage from './components/pages/LogoutPage.tsx';
import AdminPage from './components/pages/AdminPage.tsx';
import AddTemplatePage from './components/pages/AddTemplatePage.tsx';

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
                      <AdminPage />
                    </PermissionGuard>
                  }
                />
              </Route>
              <Route path="/logout" element={<LogoutPage />} />
              <Route path="/addTemplate" element={<AddTemplatePage />} />
            </Routes>
          </main>
          <footer>footer</footer>
        </div>
      </Router>
    </AuthProvider>
  );
};

export default App;
