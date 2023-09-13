import { RouterProvider } from 'react-router-dom';
import router from './router';
import GlobalStyle from './Globalstyles';
import checkLoggedIn from './functions/CheckLoggedIn';
import refresh from './functions/Refresh';
import { useEffect } from 'react';

function App() {
  useEffect(() => {
    refresh();
    checkLoggedIn();
  }, []);

  return (
    <div>
      <GlobalStyle />
      <RouterProvider router={router}></RouterProvider>
    </div>
  );
}

export default App;
