import axios from 'axios';
import SaveCookies from './SaveCookie';
import { useNavigate } from 'react-router-dom';

const CLIENT_ID = `${process.env.REACT_APP_KAKAO_CLIENT_ID}`;
const REDIRECT_URI = `${process.env.REACT_APP_KAKAO_REDIRECTURL}`;
const kakaoAuthURL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

// 카카오 로그인 버튼 누를 시 인증 URL로 이동 후 카카오 로그인 상태 localStorage에 저장
export const KakaoCallback = () => {
  window.location.href = kakaoAuthURL;
  localStorage.setItem('kakaoLoggedIn', 'true');
};

export const GetKakaoToken = async (code) => {
  const navigate = useNavigate();

  const res = await axios.post('Backend_Kakao_API', code);
  const { access_token, refresh_token, expires } = res.data;
  SaveCookies(access_token, refresh_token, expires);

  navigate('/');
};
