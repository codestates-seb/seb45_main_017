import { RouterProvider } from 'react-router-dom';
import router from './router';
import GlobalStyle from './Globalstyles';

function App() {
  return (
    <div>
      <GlobalStyle />
      <RouterProvider router={router}></RouterProvider>
    </div>
  );
}

export default App;
