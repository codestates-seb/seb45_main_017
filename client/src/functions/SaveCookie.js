import cookies from 'react-cookies';

const saveCookies = (access_token, refresh_token, expires) => {
  cookies.save('access_token', access_token, { path: '/', expires });
  cookies.save('refresh_token', refresh_token, { path: '/' });
  cookies.save('expires', expires, { path: '/' });
};

export default saveCookies;
