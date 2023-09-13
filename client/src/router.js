import { createBrowserRouter } from 'react-router-dom';
import MainPage from './pages/MainPage';

const router = createBrowserRouter([
  {
    path: '/recipes',
    element: <MainPage />,
    children: [
      {
        path: ':name',
        element: <MainPage />,
        children: [
          {
            path: ':page',
            element: <MainPage />,
          },
        ],
      },
    ],
  },
]);

export default router;
