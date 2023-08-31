import { styled } from 'styled-components';
import TextBox from '../components/Login/TextBox';
import LoginForm from '../components/Login/LoginForm';
import SocialBox from '../components/Login/SocialBox';

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
