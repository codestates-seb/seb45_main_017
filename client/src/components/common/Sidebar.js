import { styled } from 'styled-components';
import { useSelector } from 'react-redux';

const Container = styled.div`
  position: fixed;
  background-color: aqua;
  width: 250px;
  flex-direction: column;
  height: 100vh;
  left: 0;
  top: 55px;
  padding: 20px;
  transition: 0.5s linear;
  div {
    background-color: #ededed;
    width: 200px;
  }
  .sidebar-user {
    width: 200px;
    display: flex;
    padding: 15px;
    border: 1px solid black;
    border-radius: 10px;
    transition: 0.5s linear;

    span {
      margin-left: 10px;
    }
  }
  .sidebar-menu {
    padding: 10px 15px;
    margin-top: 20px;
    border-radius: 10px;
    height: 500px;
    transition: 0.5s linear;
    overflow: hidden;
    div {
      padding: 15px 0px;
      border-bottom: 1px solid #dddddd;

      span {
        cursor: pointer;
      }
    }
    div:last-child {
      border-bottom: none;
    }
  }

  &.hidden {
    width: 0;
    left: -300px;
    transition: 0ms.5 linear;
    overflow: hidden;
    .sidebar-user,
    .sidebar-menu {
      transition: 0.5s linear;
      width: 0px;
      overflow: hidden;
    }
  }
`;

const Sidebar = () => {
  let sideState = useSelector((state) => {
    return state.sideState;
  });

  return (
    <Container className={sideState ? 'hidden' : null}>
      <div className="sidebar-user">
        {/* API 통해서 유저 이미지 받아와서 적용하기 */}
        <div>{`이미지`}</div>
        {/* API 통해서 유저명 받아와서 적용하기 */}
        <span>{`유저명`}</span>
      </div>
      <div className="sidebar-menu">
        <div>
          <span>퀵메뉴 1</span>
        </div>
        <div>
          <span>퀵메뉴 2</span>
        </div>
        <div>
          <span>퀵메뉴 3</span>
        </div>
        <div>
          <span>퀵메뉴 4</span>
        </div>
      </div>
    </Container>
  );
};

export default Sidebar;
