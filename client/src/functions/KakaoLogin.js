import axios from 'axios';
import { useEffect } from 'react';

// 1. 방법1

const CLIENT_ID = `${process.env.REACT_APP_KAKAO_CLIENT_ID}`;
const REDIRECT_URI = `${process.env.REACT_APP_KAKAO_REDIRECTURL}`;
const kakaoAuthURL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

// 카카오 로그인 버튼 누를 시 인증 URL로 이동 후 카카오 로그인 상태 localStorage에 저장
export const kakaoCallback = () => {
  window.location.href = kakaoAuthURL;
  localStorage.setItem('kakaoLoggedIn', 'true');
};

export const getKakaoToken = async (code) => {
  const respond = await axios.post('Backend_Kakao_API', code);
  const { acces_token } = respond.data;
};
