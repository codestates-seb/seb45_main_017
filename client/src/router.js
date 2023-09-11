import { createBrowserRouter } from 'react-router-dom';
import MainPage from './pages/MainPage';

const router = createBrowserRouter([
  {
    path: '/recipes/:content/:page',
    element: <MainPage />,
  },
]);

export default router;
