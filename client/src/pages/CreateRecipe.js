import { styled } from 'styled-components';
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Header from '../components/common/Header';

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  align-items: center;
  margin-top: 30px;
  .postBtn {
    margin-bottom: 30px;
  }
  .fileInput {
    opacity: 0;
    width: 0.1px;
    height: 0.1px;
    position: absolute;
    overflow: hidden;
    z-index: -1;
  }
  .custom-label {
    background-color: #3498db;
    color: #fff;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    &:hover {
      background-color: rgba(0, 109, 205, 1);
    }
  }
  .fileInput + .custom-label {
    display: inline-block;
  }
  .fileInput + .custom-label + span {
    display: none;
  }

  .Crtitle {
    font-weight: bold;
    font-size: 50px;
    padding: 10px;
    border-bottom: 1px solid rgba(181, 181, 181, 1);
    width: 80%;
    margin-top: 60px;
  }
  .recipe-form {
    border: none;
    width: 600px;
    height: 323px;
    margin: 10px;
    text-align: center;
    background-color: rgba(217, 217, 217, 1);
  }
  .fileInput {
    margin-bottom: 25px;
  }
  .preview-image {
    width: 600px;
    height: 323px;
    z-index: 100000;
  }
  .error-message {
    color: red;
    margin-bottom: 20px;
    font-size: 14px;
  }
  .custom-select {
    height: 38px;
    padding: 5px;
    font-size: 16px;
    width: 200px;
  }
  .NameInput {
    margin-top: 10px;
    margin-bottom: 30px;
    margin-right: 100px;
    display: flex;
    font-weight: bold;
    > input {
      margin-left: 20px;
      height: 30px;
      width: 590px;
      border: 8px solid rgba(217, 217, 217, 1);
      border-radius: 8px;
    }
  }
  .CategoryInput {
    padding: 10px;
    display: flex;
    margin-bottom: 10px;
    font-weight: bold;
    margin-right: 120px;
    > select {
      margin-left: 20px;
      width: 610px;
      height: 60px;
      border: 8px solid rgba(217, 217, 217, 1);
      border-radius: 8px;
    }
  }
  .NutrientInput {
    padding: 10px;
    display: flex;
    margin-bottom: 10px;
    font-weight: bold;
    margin-right: 90px;
    > textarea {
      margin-left: 20px;
      height: 30px;
      width: 590px;
      border: 8px solid rgba(217, 217, 217, 1);
      border-radius: 8px;
    }
  }
  .PlanInput {
    padding: 10px;
    display: flex;
    font-weight: bold;
    margin-right: 110px;
    > textarea {
      height: 150px;
      margin-left: 20px;
      width: 590px;
      border: 8px solid rgba(217, 217, 217, 1);
      border-radius: 8px;
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
    cursor: pointer;
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
  const [selectedImage, setSelectedImage] = useState(null);
  const [error, setError] = useState('');
  const [imagePreviewUrl, setImagePreviewUrl] = useState('');

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

  const handleImageSelect = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedImage(file);

      const imageUrl = URL.createObjectURL(file);
      setImagePreviewUrl(imageUrl);
    }
  };

  const handleRecipeSubmit = async () => {
    if (
      !validateRecipeName() ||
      !validateNutrients() ||
      !validateCookingMethod() ||
      !validateSelectedCategory() ||
      /[!@#$%^&*(),.?":{}|<>]/.test(recipeName) ||
      !selectedImage
    ) {
      setError(
        '모든 필드에 특수문자를 제외한 값을 채워주세요 및 이미지를 선택하세요',
      );
      return;
    }

    setError('');

    const recipeData = new FormData();
    recipeData.append('memberId', '1');
    recipeData.append('recipeTitle', recipeName);
    recipeData.append('recipeType', selectedCategory);
    recipeData.append('nutrition', nutrients);
    recipeData.append('recipeBody', cookingMethod);
    recipeData.append('image', selectedImage);

    const url = process.env.REACT_APP_API_URL;

    try {
      const response = await axios.post(`${url}/recipes`, recipeData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      console.log('Recipe created:', response.data);
      navigate('/');
    } catch (error) {
      console.error('Error creating recipe:', error);
    }
  };

  return (
    <>
      <Header />
      <InputContainer>
        <div className="Crtitle">레시피 작성하기</div>
        <div className="recipe-form">
          {imagePreviewUrl && (
            <div className="image-preview">
              <img
                src={imagePreviewUrl}
                alt="미리보기"
                className="preview-image"
              />
            </div>
          )}
        </div>
        <input
          id="fileInput"
          className="fileInput"
          type="file"
          accept="image/*"
          onChange={handleImageSelect}
        />
        <label htmlFor="fileInput" className="custom-label">
          파일 선택
        </label>
        <div className="NameInput">
          <div className="section">요리명</div>
          <input
            value={recipeName}
            onChange={(e) => setRecipeName(e.target.value)}
            placeholder="ex) 새우두부계란찜"
          />
        </div>
        {!validateRecipeName() && (
          <div className="error-message">요리명을 입력하세요.</div>
        )}
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
        </div>
        {!validateSelectedCategory() && (
          <div className="error-message">요리종류를 선택하세요.</div>
        )}
        <div className="NutrientInput">
          <div className="section">영양분</div>
          <textarea
            value={nutrients}
            onChange={(e) => setNutrients(e.target.value)}
            placeholder="ex) 지방 15g, 나트륨 30g"
          />
        </div>
        {!validateNutrients() && (
          <div className="error-message">영양분 정보를 입력하세요.</div>
        )}
        <div className="PlanInput">
          <div className="section">조리방법</div>
          <textarea
            value={cookingMethod}
            onChange={(e) => setCookingMethod(e.target.value)}
            placeholder="ex) 1. 손질된 새우를 끓는 물에 데쳐 건진다"
          />
        </div>
        {!validateCookingMethod() && (
          <div className="error-message">조리방법을 입력하세요.</div>
        )}
        {error && <div className="error-message">{error}</div>}
        <button className="postBtn" onClick={handleRecipeSubmit}>
          레시피 생성
        </button>
      </InputContainer>
    </>
  );
};

export default CreateRecipe;
