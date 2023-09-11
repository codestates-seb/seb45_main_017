import cookies from 'react-cookies';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const navigate = useNavigate();
  cookies.remove('access_token');
  cookies.remove('refresh_token');
  cookies.remove('expires');
  localStorage.removeItem('kakaoLoggedIn');
  localStorage.removeItem('naverLoggedIn');

  navigate('/login');
};

export default Logout;
