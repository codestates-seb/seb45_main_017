import { styled } from 'styled-components';

const TextBoxComponent = styled.div`
  display: flex;
  text-align: center;
  color: rgb(73, 125, 255);

  span {
    font-size: 40px;
    font-weight: 700;
  }
`;

const SignTitle = () => {
  return (
    <TextBoxComponent>
      <span>회원가입</span>
    </TextBoxComponent>
  );
};

export default SignTitle;
