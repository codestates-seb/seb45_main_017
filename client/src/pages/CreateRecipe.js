import { styled } from 'styled-components';
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  align-items: center;
  height: 100vh;
  .error-message {
    color: red;
    margin-top: 10px;
    margin-left: 10px;
    font-size: 14px;
  }
  .custom-select {
    height: 38px;
    padding: 5px;
    font-size: 16px;
    width: 200px;
  }
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
  .CategoryInput {
    padding: 10px;
    display: flex;
    margin-bottom: 30px;
    > select {
      margin-left: 20px;
      width: 630px;
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
  const [selectedCategory, setSelectedCategory] = useState('');
  const [error, setError] = useState('');

  const navigate = useNavigate();

  const validateRecipeName = () => {
    return recipeName.trim() !== '';
  };

  const validateNutrients = () => {
    return nutrients.trim() !== '';
  };

  const validateCookingMethod = () => {
    return cookingMethod.trim() !== '';
  };

  const validateSelectedCategory = () => {
    return selectedCategory !== '';
  };

  const handleRecipeSubmit = () => {
    if (
      !validateRecipeName() ||
      !validateNutrients() ||
      !validateCookingMethod() ||
      !validateSelectedCategory() ||
      /[!@#$%^&*(),.?":{}|<>]/.test(recipeName)
    ) {
      setError('모든 필드에 특수문자를 제외한 값을 채워주세요');
      return;
    }

    setError('');

    const recipeData = {
      recipeTitle: recipeName,
      nutrition: nutrients,
      recipeBody: cookingMethod,
      recipeType: selectedCategory,
    };

    const apiUrl = process.env.REACT_APP_API_URL;

    axios
      .post(`${apiUrl}/recipes`, recipeData)
      .then((response) => {
        console.log('Recipe created:', response.data);
        navigate('/mainpage');
      })
      .catch((error) => {
        console.error('Error creating recipe:', error);
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
            placeholder="ex) 새우두부계란찜"
          />
          {!validateRecipeName() && (
            <div className="error-message">요리명을 입력하세요.</div>
          )}
        </div>
        <div className="CategoryInput">
          <div className="section">요리종류</div>
          <select
            className="custom-select"
            value={selectedCategory}
            onChange={(e) => setSelectedCategory(e.target.value)}
          >
            <option className="options" value="">
              요리종류를 선택해주세요
            </option>
            <option value="일품">일품</option>
            <option value="후식">후식</option>
            <option value="반찬">반찬</option>
            <option value="밥">밥</option>
            <option value="국&찌개">국&찌개</option>
          </select>
          {!validateSelectedCategory() && (
            <div className="error-message">요리종류를 선택하세요.</div>
          )}
        </div>
        <div className="NutrientInput">
          <div className="section">영양분</div>
          <textarea
            value={nutrients}
            onChange={(e) => setNutrients(e.target.value)}
            placeholder="ex) 지방 15g, 나트륨 30g"
          />
          {!validateNutrients() && (
            <div className="error-message">영양분 정보를 입력하세요.</div>
          )}
        </div>
        <div className="PlanInput">
          <div className="section">조리방법</div>
          <textarea
            value={cookingMethod}
            onChange={(e) => setCookingMethod(e.target.value)}
            placeholder="ex) 1. 손질된 새우를 끓는 물에 데쳐 건진다"
          />
          {!validateCookingMethod() && (
            <div className="error-message">조리방법을 입력하세요.</div>
          )}
        </div>
        {error && <div className="error-message">{error}</div>}
        <button onClick={handleRecipeSubmit}>레시피 생성</button>
      </InputContainer>
    </>
  );
};

export default CreateRecipe;
