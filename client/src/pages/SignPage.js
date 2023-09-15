import { styled } from 'styled-components';
import Header from '../components/common/Header';
import SignTitle from '../components/Signup/SignTitle';
import SignForm from '../components/Signup/SignForm';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
`;

const SignPage = () => {
  return (
    <Container>
      <Header></Header>
      <SignTitle></SignTitle>
      <SignForm></SignForm>
    </Container>
  );
};

export default SignPage;
