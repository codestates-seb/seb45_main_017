import { styled } from 'styled-components';
import { kakaoCallback } from '../../functions/KakaoLogin';
import { naverCallback } from '../../functions/NaverLogin';

const SocialBoxComponent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  width: 400px;
  margin-top: -60px;
  border-top: 1px solid #c7c7c7;
  > span {
    margin-top: 10px;
    font-size: 15px;
    color: #7e7e7e;
  }

  .social {
    display: flex;
    margin-top: 20px;
    width: 100%;
    justify-content: space-around;
    span {
      color: #b5b5b5;
      font-size: 13px;
    }
    div {
      cursor: pointer;
    }
    .kakao {
      span:first-child {
        background: linear-gradient(to right, #f6c61e, #fde732);
        background-clip: text;
        -webkit-background-clip: text;
        color: transparent;
      }
    }
    .naver {
      span:first-child {
        background: linear-gradient(to right, #00a155, #04c85a);
        background-clip: text;
        -webkit-background-clip: text;
        color: transparent;
      }
    }
  }
`;

const SocialBox = () => {
  return (
    <SocialBoxComponent>
      <span>소셜 계정으로 로그인</span>
      <div className="social">
        <div className="kakao" role="presentation" onClick={kakaoCallback}>
          <span>카카오 </span>
          <span>계정으로 로그인</span>
        </div>
        <div className="naver" role="presentation" onClick={naverCallback}>
          <span>네이버 </span>
          <span>계정으로 로그인</span>
        </div>
      </div>
    </SocialBoxComponent>
  );
};

export default SocialBox;
