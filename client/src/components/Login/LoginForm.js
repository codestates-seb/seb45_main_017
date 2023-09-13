import { useForm } from 'react-hook-form';
import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { kakaoCallback } from '../../functions/KakaoLogin';

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
      // 백엔드 측 access_token 완료될 때까지 주석 처리
      const res = await axios.post(
        'https://4e4e-210-97-104-152.ngrok-free.app/member/login',
        data,
      );
      const { access_token } = res.data;
      axios.defaults.headers.common['Authorization'] = `Bearer ${access_token}`;
      localStorage.setItem('access_token', access_token);
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
          {...register('email', {
            required: '아이디를 입력해주세요',
          })}
          placeholder="이메일"
        ></input>
        <span> {errors?.email?.message}</span>
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
