import axios from 'axios';
import saveCookies from './SaveCookie';
import { redirect } from 'react-router-dom';

const clientId = process.env.REACT_APP_NAVER_CLIENT_ID;
const redirectId = process.env.REACT_APP_NAVER_REDIRECTURL;
const state = 'true';
const naverAuthUrl =
  'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' +
  clientId +
  '&redirect_uri=' +
  redirectId +
  '&state=' +
  state;

// 네이버 로그인 버튼 누를 시 지정된 URL로 인증 요청 및 네이버 로그인 상태 저장
export const naverCallback = () => {
  window.location.href = naverAuthUrl;
  localStorage.setItem('naverLoggedIn', 'true');
};

export const getNaverToken = async (code) => {
  const res = await axios.post('Backend_Naver_API', code);
  const { access_token, refresh_token, expires } = res.data;
  saveCookies(access_token, refresh_token, expires);
  redirect('/');
};
