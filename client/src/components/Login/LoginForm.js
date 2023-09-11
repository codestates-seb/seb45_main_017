import { useForm } from 'react-hook-form';
import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { kakaoCallback } from '../../functions/KakaoLogin';
import SaveCookies from '../../functions/SaveCookie';

const LoginFormComponent = styled.div`
  form {
    display: flex;
    flex-direction: column;
    input {
      padding: 10px 10px;
      margin-bottom: 10px;
      outline: none;
      width: 250px;
      border-radius: 5px;
    }
    button {
      padding: 10px 10px;
      border-radius: 5px;
      border: none;
      cursor: pointer;
    }
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
        'https://b151-119-69-252-33.ngrok-free.app/login',
        data,
      );
      const { access_token, refresh_token, expires } = res.data;
      SaveCookies(access_token, refresh_token, expires);
      navigate('/');
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
            required: '아이디를 입력해주세요',
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
    </LoginFormComponent>
  );
};

export default LoginForm;
