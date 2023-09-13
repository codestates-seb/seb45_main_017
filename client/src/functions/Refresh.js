import cookies from 'react-cookies';
import axios from 'axios';

const refresh = async () => {
  const expires = cookies.load('expires');
  // 만료시간 계산하여 만료 시간 임박 시 refresh 토큰 이용하여 access 토큰 재발급 후 저장
  if (expires) {
    const refreshToken = cookies.load('refresh_token');
    const newAccessToken = await axios.post('refresh_API', refreshToken);
    cookies.save('access_token', newAccessToken, { path: '/', expires });
  }
};

export default refresh;
