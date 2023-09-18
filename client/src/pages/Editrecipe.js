import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { styled } from 'styled-components';
import Header from '../components/common/Header';

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  align-items: center;
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
    z-index: 100000;
    height: 323px;
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

const EditRecipe = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [recipeData, setRecipeData] = useState({
    recipeName: '',
    nutrients: '',
    cookingMethod: '',
    selectedCategory: '',
    selectedImage: null,
    error: '',
    imagePreviewUrl: '',
    memberId: '',
  });

  useEffect(() => {
    const apiUrl = process.env.REACT_APP_API_URL;
    axios
      .get(`${apiUrl}/recipes/1`)
      .then((response) => {
        const {
          recipeName,
          nutrients,
          cookingMethod,
          selectedCategory,
          imageUrl,
          memberId,
        } = response.data;
        setRecipeData((prevData) => ({
          ...prevData,
          recipeName,
          nutrients,
          cookingMethod,
          selectedCategory,
          imagePreviewUrl: imageUrl,
          memberId,
        }));
      })
      .catch((error) => {
        console.error('Error fetching recipe:', error);
      });
  }, [id]);

  const validateRecipeName = () => {
    return recipeData.recipeName.trim() !== '';
  };

  const validateNutrients = () => {
    return recipeData.nutrients.trim() !== '';
  };

  const validateCookingMethod = () => {
    return recipeData.cookingMethod.trim() !== '';
  };

  const validateSelectedCategory = () => {
    return recipeData.selectedCategory !== '';
  };

  const handleImageSelect = (e) => {
    const file = e.target.files[0];
    if (file) {
      setRecipeData({
        ...recipeData,
        selectedImage: file,
        imagePreviewUrl: URL.createObjectURL(file),
      });
    }
  };

  const handleRecipeSubmit = async () => {
    const formData = new FormData();
    formData.append('memberId', recipeData.memberId);
    formData.append('recipeTitle', recipeData.recipeName);
    formData.append('nutrition', recipeData.nutrients);
    formData.append('recipeBody', recipeData.cookingMethod);
    formData.append('recipeType', recipeData.selectedCategory);
    if (recipeData.selectedImage) {
      formData.append('file', recipeData.selectedImage);
    }

    const apiUrl = process.env.REACT_APP_API_URL;
    try {
      await axios.patch(`${apiUrl}/recipes/1`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      navigate(`/recipes-des/1`);
    } catch (error) {
      console.error('Error updating recipe:', error);
    }
  };

  return (
    <>
      <Header />
      <InputContainer>
        <div className="Crtitle">레시피 수정하기</div>
        <div className="recipe-form">
          {recipeData.imagePreviewUrl && (
            <div className="image-preview">
              <img
                src={recipeData.imagePreviewUrl}
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
            value={recipeData.recipeName}
            onChange={(e) =>
              setRecipeData({ ...recipeData, recipeName: e.target.value })
            }
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
            value={recipeData.selectedCategory}
            onChange={(e) =>
              setRecipeData({
                ...recipeData,
                selectedCategory: e.target.value,
              })
            }
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
            value={recipeData.nutrients}
            onChange={(e) =>
              setRecipeData({ ...recipeData, nutrients: e.target.value })
            }
            placeholder="ex) 지방 15g, 나트륨 30g"
          />
        </div>
        {!validateNutrients() && (
          <div className="error-message">영양분 정보를 입력하세요.</div>
        )}
        <div className="PlanInput">
          <div className="section">조리방법</div>
          <textarea
            value={recipeData.cookingMethod}
            onChange={(e) =>
              setRecipeData({ ...recipeData, cookingMethod: e.target.value })
            }
            placeholder="ex) 1. 손질된 새우를 끓는 물에 데쳐 건진다"
          />
        </div>
        {!validateCookingMethod() && (
          <div className="error-message">조리방법을 입력하세요.</div>
        )}
        {recipeData.error && (
          <div className="error-message">{recipeData.error}</div>
        )}
        <button className="postBtn" onClick={handleRecipeSubmit}>
          레시피 수정
        </button>
      </InputContainer>
    </>
  );
};

export default EditRecipe;
