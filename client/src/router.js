import { createBrowserRouter } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import SignPage from './pages/SignPage';

const router = createBrowserRouter([
  { path: '/login', element: <LoginPage></LoginPage> },
  { path: '/sign', element: <SignPage></SignPage> },
]);

export default router;
