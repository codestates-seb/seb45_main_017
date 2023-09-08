import { styled } from 'styled-components';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { useNavigate } from 'react-router';

const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
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
    span {
      opacity: 0.5;
      font-size: 13px;
      margin-bottom: 10px;
      margin-left: 10px;
    }
  }
`;

const SignPage = () => {
  const navigate = useNavigate();

  const {
    register,
    watch,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const checkPassword = () => {
    if (watch().password !== watch().password2) {
      return '입력한 비밀번호가 일치하지 않습니다.';
    }
  };

  const onValid = async ({ username, email, password }) => {
    try {
      const formData = { username, email, password };
      console.log(formData);
      await axios.post(
        'https://b151-119-69-252-33.ngrok-free.app/member',
        formData,
      );
      navigate('/login');
    } catch (error) {
      alert('이미 존재하는 아이디입니다.');
      console.log(error);
    }
  };

  return (
    <Container>
      <form onSubmit={handleSubmit(onValid)}>
        <input
          {...register('username', {
            required: '유저명을 입력해주세요',
            minLength: { value: 2, message: '최소 2글자를 입력해야 합니다.' },
          })}
          placeholder="유저명"
        ></input>
        <span>{errors?.username?.message}</span>
        <input
          {...register('email', {
            required: '이메일을 입력해주세요',
            pattern: {
              value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
              message: '올바른 이메일 형식을 입력해주세요',
            },
          })}
          placeholder="이메일"
          type="email"
        ></input>
        <span> {errors?.email?.message}</span>

        <input
          {...register('password', {
            required: '패스워드를 입력해주세요',
            minLength: {
              value: 8,
              message: '최소 8글자 이상 입력해주세요',
            },
          })}
          placeholder="패스워드"
          type="password"
        ></input>
        <span> {errors?.password?.message}</span>
        <input
          {...register('password2', {
            required: '입력한 패스워드의 확인이 필요합니다',
            validate: checkPassword,
          })}
          placeholder="패스워드 확인"
          type="password"
        ></input>
        <span> {errors?.password2?.message}</span>
        <button>회원가입</button>
      </form>
    </Container>
  );
};

export default SignPage;
