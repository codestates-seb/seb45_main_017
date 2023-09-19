import cookies from 'react-cookies';
import axios from 'axios';

const checkLoggedIn = () => {
  const accessToken = cookies.load('access_token');
  if (accessToken) {
    axios.defaults.headers.common['Authorization'] = `${accessToken}`;
    // 상황 고려해서 redux에 로그인 상태 여부 확인하는 상태 값 추가하기
  }
};

export default checkLoggedIn;
