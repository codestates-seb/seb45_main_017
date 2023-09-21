import { styled } from 'styled-components';
import cookies from 'react-cookies';
import { useNavigate } from 'react-router-dom';
import { changeSideState } from '../../store';
import { useDispatch } from 'react-redux';
import logout from '../../functions/Logout';
import Sidebar from './Sidebar';
import { useState } from 'react';

const HeaderComponent = styled.div`
  position: fixed;
  top: 0;
  width: 100%;
  height: 55px;
  background-color: #497dfe;
`;

const Container = styled.div`
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(1, 55px);
  .hideHeader {
    display: none;
  }
  div {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  svg {
    font-size: 20px;
    fill: #ffff;
  }

  .sidebar {
    justify-content: flex-start;
    margin-left: 40px;
  }

  .title span {
    cursor: pointer;
    font-size: 18px;
    color: #ffff;
    font-weight: 600;
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
  justify-content: flex-start;
  span {
    width: 70px;
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
  let [hideHeader, setHideHeader] = useState(true);
  let dispatch = useDispatch();

  const loggedIn = cookies.load('access_token');
  const navigate = useNavigate();

  return (
    <HeaderComponent>
      <Container>
        <div
          className="sidebar"
          role="presentation"
          onClick={() => {
            dispatch(changeSideState());
          }}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 448 512"
          >
            <path d="M0 96C0 78.3 14.3 64 32 64H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32H416c17.7 0 32 14.3 32 32z" />
          </svg>
        </div>
        <div className="title">
          <span
            role="presentation"
            onClick={() => {
              navigate('/recipes');
            }}
          >
            Search Chef
          </span>
        </div>
        <div
          className="user"
          role="presentation"
          onClick={() => {
            setHideHeader(!hideHeader);
          }}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 448 512"
          >
            <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" />
          </svg>
          <div className={`bubble ${hideHeader ? 'hideHeader' : null}`}>
            {loggedIn ? (
              <BubbleText>
                <span
                  role="presentation"
                  onClick={() => {
                    logout();
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
                <span role="presentation" onClick={() => [navigate('/signup')]}>
                  회원가입
                </span>
              </BubbleText>
            )}
          </div>
        </div>
      </Container>
      <Sidebar></Sidebar>
    </HeaderComponent>
  );
};

export default Header;
