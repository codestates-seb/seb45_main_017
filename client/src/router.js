import { createBrowserRouter } from 'react-router-dom';
import MyPage from './pages/MyPage';
import RecipeDes from './pages/RecipeDes';
import CreateRecipe from './pages/CreateRecipe';

const router = createBrowserRouter([
  {
    path: '/mypage',
    element: <MyPage />,
  },
  {
    path: '/recipe-des',
    element: <RecipeDes />,
  },
  {
    path: '/recipe-create',
    element: <CreateRecipe />,
  },
]);

export default router;
