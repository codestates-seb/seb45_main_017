import { styled } from 'styled-components';
import React, { useState } from 'react';
import axios from 'axios';

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  align-items: center;
  height: 100vh;
  .NameInput {
    padding: 10px;
    margin-bottom: 30px;
    display: flex;
    > input {
      margin-left: 20px;
      height: 30px;
      width: 600px;
    }
  }
  .NutrientInput {
    padding: 10px;
    display: flex;
    margin-bottom: 30px;
    > textarea {
      margin-left: 20px;
      height: 30px;
      width: 600px;
    }
  }
  .PlanInput {
    padding: 10px;
    margin-top: 10px;
    display: flex;
    > textarea {
      height: 100px;
      margin-left: 20px;
      width: 625px;
    }
  }
  .section {
    display: flex;
    align-items: center;
    font-size: 30px;
  }
  button {
    background-color: #569aff;
    border: none;
    border-radius: 15px;
    width: 110px;
    height: 60px;
    font-size: 20px;
    color: white;
    &:hover {
      background-color: rgba(0, 109, 205, 1);
    }
  }
`;

const CreateRecipe = () => {
  const [recipeName, setRecipeName] = useState('');
  const [nutrients, setNutrients] = useState('');
  const [cookingMethod, setCookingMethod] = useState('');

  const handleRecipeSubmit = () => {
    const recipeData = {
      name: recipeName,
      nutrient: nutrients,
      method: cookingMethod,
    };

    axios
      .post('/api/recipes', recipeData)
      .then((response) => {
        // 요청 성공 시 처리
        console.log('Recipe created:', response.data);
        // 필요한 추가 작업 수행
      })
      .catch((error) => {
        // 요청 실패 시 처리
        console.error('Error creating recipe:', error);
        // 에러 처리 및 사용자에게 알림 표시
      });
  };

  return (
    <>
      <InputContainer>
        <div className="NameInput">
          <div className="section">요리명</div>
          <input
            value={recipeName}
            onChange={(e) => setRecipeName(e.target.value)}
          />
        </div>
        <div className="NutrientInput">
          <div className="section">영양분</div>
          <textarea
            value={nutrients}
            onChange={(e) => setNutrients(e.target.value)}
          />
        </div>
        <div className="PlanInput">
          <div className="section">조리방법</div>
          <textarea
            value={cookingMethod}
            onChange={(e) => setCookingMethod(e.target.value)}
          />
        </div>
        <button onClick={handleRecipeSubmit}>레시피 생성</button>
      </InputContainer>
    </>
  );
};

export default CreateRecipe;
