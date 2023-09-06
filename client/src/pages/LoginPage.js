import { styled } from 'styled-components';
import TextBox from '../components/Login/TextBox';
import LoginForm from '../components/Login/LoginForm';
import SocialBox from '../components/Login/SocialBox';
import { useEffect } from 'react';

const Body = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
`;

const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-direction: column;
  background-color: aqua;
  width: 500px;
  height: 500px;
`;

const LoginPage = () => {
  // 페이지 로드 시 소셜 로그인 조건을 충족하면 해당하는 소셜 로그인 기능 작동

  return (
    <Body>
      <Container>
        <TextBox></TextBox>
        <LoginForm></LoginForm>
        <SocialBox></SocialBox>
      </Container>
    </Body>
  );
};

export default LoginPage;
