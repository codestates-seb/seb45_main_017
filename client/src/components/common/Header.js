import { styled } from 'styled-components';
import cookies from 'react-cookies';
import { useNavigate } from 'react-router-dom';

const HeaderComponent = styled.div`
  position: fixed;
  top: 0;
  width: 100%;
  height: 55px;
  background-color: #497dfe;
`;

const Container = styled.div`
  max-width: 900px;
  height: 100%;
  margin: auto;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(1, 55px);
  div {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .bubble {
    position: absolute;
    width: 100px;
    padding: 10px 0px;
    background-color: #303d4b;
    border-radius: 10px;
    z-index: 1;
    top: 50px;
    &::after {
      content: '';
      position: absolute;
      border-style: solid;
      border-width: 0 15px 15px;
      border-color: #303d4b transparent;
      display: block;
      width: 0;
      z-index: 1;
      top: -10px;
    }
  }
`;

const BubbleText = styled.div`
  display: flex;
  flex-direction: column;
  span {
    margin-top: 5px;
    font-size: 13px;
    color: #ffff;
    cursor: pointer;
    &:not(div:first-child) {
      margin-top: 10px;
    }
  }
`;

const Header = () => {
  const loggedIn = cookies.load('access_token');
  const navigate = useNavigate();

  return (
    <HeaderComponent>
      <Container>
        <div className="sidebar"></div>
        <div className="title">
          <span>Search Chef</span>
        </div>
        <div className="user">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 448 512"
          >
            <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" />
          </svg>
          <div className="bubble">
            {loggedIn ? (
              <BubbleText>
                <span
                  role="presentation"
                  onClick={() => {
                    navigate('/');
                  }}
                >
                  로그아웃
                </span>
              </BubbleText>
            ) : (
              <BubbleText>
                <span
                  role="presentation"
                  onClick={() => {
                    navigate('/login');
                  }}
                >
                  로그인
                </span>
                <span role="presentation" onClick={() => [navigate('/sign')]}>
                  회원가입
                </span>
              </BubbleText>
            )}
          </div>
        </div>
      </Container>
    </HeaderComponent>
  );
};

export default Header;
