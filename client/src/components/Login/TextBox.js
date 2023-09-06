import { styled } from 'styled-components';

const TextBoxComponent = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
  width: 300px;
  line-height: 1.3;
  h4 {
    font-size: 22px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  span {
    opacity: 0.5;
  }
`;

const TextBox = () => {
  return (
    <TextBoxComponent>
      <h4>여러 사람들의 다양한 레시피들을 지금 바로 만나보세요.</h4>
      <span>
        모두쉐프는 사람들의 다양한 레시피들을 만나보고 자신만의 레시피를 소개할
        수 있는 레시피 사이트입니다.
      </span>
    </TextBoxComponent>
  );
};

export default TextBox;
