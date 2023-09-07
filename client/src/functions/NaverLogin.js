import axios from 'axios';

const clientId = process.env.NAVER_CLIENT_ID;
const redirectId = process.env.NAVER_REDIRECT_ID;
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
  const respond = await axios.post('Backend_Naver_API', code);
  const { acces_token } = respond.data;
};
