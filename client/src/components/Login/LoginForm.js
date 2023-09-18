import { useForm } from 'react-hook-form';
import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import SaveCookies from '../../functions/SaveCookie';

const LoginFormComponent = styled.div`
  form {
    display: flex;
    flex-direction: column;
    input {
      padding: 10px 10px;

      outline: none;
      width: 370px;
      border: 1px solid black;
      border-radius: 5px;
      background-color: rgb(245, 245, 245);
    }
    button {
      margin-top: 15px;
      padding: 10px 10px;
      border-radius: 5px;
      border: none;
      background-color: rgb(73, 125, 255);
      color: white;
      cursor: pointer;
    }
    span {
      font-size: 13px;
      margin: 10px;
      opacity: 0.6;
    }
  }
`;

const SignUpComponent = styled.div`
  margin-top: 15px;
  font-size: 12px;
  text-align: right;
  span:nth-child(2) {
    color: #497dff;
    cursor: pointer;
  }
`;

const LoginForm = () => {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  // 로그인 폼 제출 시 작동 함수
  const onValid = async (data) => {
    try {
      const res = await axios.post(
        'https://82ed-119-69-252-33.ngrok-free.app/login',
        data,
      );
      const { access_token, refresh_token, expires } = res.data;
      console.log(res);
      SaveCookies(access_token, refresh_token, expires);

      navigate('/recipes');
    } catch (error) {
      alert('입력한 아이디 혹은 비밀번호가 올바른지 확인해주십시요');
      console.log(error);
    }
  };

  return (
    <LoginFormComponent>
      <form onSubmit={handleSubmit(onValid)}>
        <input
          {...register('username', {
            required: '이메일을 입력해주세요',
          })}
          placeholder="이메일"
        ></input>
        <span> {errors?.username?.message}</span>
        <input
          {...register('password', {
            required: '비밀번호를 입력해주세요',
          })}
          placeholder="비밀번호"
          type="password"
        ></input>
        <span> {errors?.password?.message}</span>
        <button>로그인</button>
      </form>
      <SignUpComponent>
        <span>아직 회원이 아니신가요? </span>
        <span
          role="presentation"
          onClick={() => {
            navigate('/signup');
          }}
        >
          회원가입 하기
        </span>
      </SignUpComponent>
    </LoginFormComponent>
  );
};

export default LoginForm;
