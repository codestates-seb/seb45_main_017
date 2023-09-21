import { createBrowserRouter } from 'react-router-dom';
import MainPage from './pages/MainPage';
import MyPage from './pages/MyPage';
import RecipeDes from './pages/RecipeDes';
import CreateRecipe from './pages/CreateRecipe';
import LoginPage from './pages/LoginPage';
import SignPage from './pages/SignPage';
import EditRecipe from './pages/Editrecipe';

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
  {
    path: '/recipe-des/:id',
    element: <RecipeDes />,
  },
  {
    path: '/recipe-create',
    element: <CreateRecipe />,
  },

  { path: '/recipe-edit/:id', element: <EditRecipe /> },

  {
    path: '/login',
    element: <LoginPage />,
  },

  { path: '/signup', element: <SignPage /> },
]);

export default router;
