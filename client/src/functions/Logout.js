import cookies from 'react-cookies';
import { redirect } from 'react-router-dom';

const logout = () => {
  cookies.remove('access_token');
  cookies.remove('refresh_token');
  cookies.remove('expires');
  localStorage.removeItem('kakaoLoggedIn');
  localStorage.removeItem('naverLoggedIn');

  redirect('/login');
};

export default logout;
