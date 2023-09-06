import { styled } from 'styled-components';

const SocialBoxComponent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  div {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 5px 10px;
    border-radius: 5px;
    margin-bottom: 10px;
    min-width: 250px;
    cursor: pointer;
    img {
      width: 50px;
      border-radius: 50%;
    }
    span {
      font-size: 15px;
    }
  }
  div:first-child {
    background-color: #fae100;
  }
  div:last-child {
    background-color: #21c603;
  }
`;

const SocialBox = () => {
  return (
    <SocialBoxComponent>
      <div>
        <img
          alt="kakao"
          src="data:image/png;base64,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"
        />
        <span>카카오 아이디로 로그인</span>
      </div>
      <div>
        <img
          alt="naver"
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZJqcc_Bh-la7KMgvFvEG2RYw2UUajhAr2-w&usqp=CAU"
        />
        <span>네이버 아이디로 로그인</span>
      </div>
    </SocialBoxComponent>
  );
};

export default SocialBox;
