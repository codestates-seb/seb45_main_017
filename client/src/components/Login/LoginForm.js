import { useForm } from 'react-hook-form';
import { styled } from 'styled-components';

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
  const { register, handleSubmit } = useForm();

  // 로그인 폼 제출 시 작동 함수
  const onValid = () => {
    console.log('login');
  };

  return (
    <LoginFormComponent>
      <form onSubmit={handleSubmit(onValid)}>
        <input
          {...register('email', { required: true })}
          placeholder="이메일을 입력해주세요"
        ></input>
        <input
          {...register('password', { required: true })}
          placeholder="비밀번호를 입력해주세요"
        ></input>
        <button>로그인</button>
      </form>
    </LoginFormComponent>
  );
};

export default LoginForm;
