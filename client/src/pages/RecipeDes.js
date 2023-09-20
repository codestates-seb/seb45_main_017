import { styled } from 'styled-components';
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link, useLocation } from 'react-router-dom';
import axios from 'axios';

const Editbtn = styled.div`
  display: flex;
  .edit {
    margin-left: 70%;
    width: 4.5%;
    border: none;
    background-color: #569aff;
    color: white;
    border-radius: 7px;
    padding: 15px;
    cursor: pointer;
    &:hover {
      background-color: rgba(0, 109, 205, 1);
    }
  }
  .delete {
    margin-left: 10px;
    background-color: #cd5c5c;
    color: white;
    border-radius: 7px;
    padding: 15px;
    cursor: pointer;
    &:hover {
      background-color: #dc143c;
    }
  }
`;

const Loadingtext = styled.div`
  .loading-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  .loading-icon {
    font-size: 24px;
    margin-right: 8px;
  }
  .loading-text {
    font-size: 20px;
  }
`;

const RecipeInfo = styled.div`
  .section3 {
    margin-left: 240px;
  }
  .nutTitle {
    font-size: 35px;
    font-weight: bold;
    background-color: rgba(234, 234, 234, 1);
    height: 10px;
    width: 75%;
    padding: 30px;
    display: flex;
    justify-content: start;
    align-items: center;
  }
  .heart {
    margin-top: 50px;
    margin-bottom: 50px;
    cursor: pointer;
    width: 30%;
    display: flex;
    margin-left: 850px;
    font-size: 25px;
    font-weight: bold;
    > span {
      text-decoration: underline;
    }
    > p {
      margin-left: 10px;
    }
  }
  .section1 {
    display: flex;
    justify-content: center;
    margin-top: 50px;
  }
  .recipe-name {
    height: 60px;
    margin-top: 30px;
    font-size: 35px;
    font-weight: bold;
    margin-left: 300px;
    border-bottom: 1px solid rgba(181, 181, 181, 1);
    width: 66%;
  }
  .hashtag {
    height: 30px;
    margin-top: 30px;
    color: blue;
    font-size: 25px;
    margin-left: 270px;
  }
  .main-img {
    > img {
      width: 600px;
      height: 500px;
      border: none;
      border-radius: 15px;
    }
  }
  .nutrient {
    width: 73%;
    height: 320px;
    margin-top: 10px;
    padding: 5px;
    color: #333333;
    font-size: 25px;
    > div {
      margin-top: 20px;
      margin-left: 25px;
    }
  }
  .side-info {
    font-size: 30px;
    margin-left: 70px;
    margin-top: 30px;
  }
  .section2 {
    display: flex;
    flex-direction: column;
    margin-left: 310px;
  }
  .title {
    margin-top: 30px;
    font-size: 35px;
    font-weight: bold;
    background-color: rgba(234, 234, 234, 1);
    height: 10px;
    width: 75%;
    padding: 30px;
    display: flex;
    justify-content: start;
    align-items: center;
  }
  .plan {
    width: 85%;
    margin-top: 10px;
    > ul {
      > li {
        margin-top: 40px;
        font-size: 25px;
        margin-left: 30px;
      }
    }
  }
`;
const CommentList = styled.div`
  margin-left: 120px;
  margin-bottom: 30px;
  width: 85%;
  margin-top: 30px;
  .comment-box {
    margin-left: 190px;
    margin-top: 30px;
    padding: 20px;
    width: 74%;
    background-color: #f5f5f5;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
  .comment-box p {
    margin: 10px;
  }
  .comment-box button {
    margin-left: 10px;
    padding: 5px 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    &:hover {
      background-color: rgba(0, 109, 205, 1);
    }
  }
  .comment-title {
    font-size: 25px;
    padding: 20px;
    color: rgba(139, 139, 139, 1);
    border-bottom: 1px solid rgba(139, 139, 139, 1);
    margin-left: 190px;
    width: 73%;
    margin-bottom: 50px;
  }
  .create-comment {
    display: flex;
    > textarea {
      margin-left: 190px;
      width: 80%;
      height: 150px;
    }
    > button {
      margin-left: 20px;
      width: 9%;
      border: none;
      background-color: #569aff;
      color: white;
      border-radius: 7px;
      padding: 15px;
      cursor: pointer;
      &:hover {
        background-color: rgba(0, 109, 205, 1);
      }
    }
  }
`;

const RecipeDes = () => {
  const [comments, setComments] = useState([
    { id: 1, content: '치킨 피자 맥주' },
    { id: 2, content: '떡볶이 파스타 마라탕' },
    { id: 3, content: '낙지 새우 곱창' },
  ]);
  const [newComment, setNewComment] = useState('');
  const [isLiked, setIsLiked] = useState(false);
  const [recipe, setRecipe] = useState(null);
  const [editingComment, setEditingComment] = useState(null);

  const { recipeId } = useParams();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const recipe_nm = searchParams.get('recipe_nm');

  const navigate = useNavigate();

  const handleEditButtonClick = () => {
    navigate('/recipe-edit');
  };
  //댓글 삭제

  const apiUrl = 'https://33fe-45-64-145-74.ngrok-free.app';

  const handleDeleteComment = (comment) => {
    axios
      .delete(`${apiUrl}/recipes/${recipeId}/comment/1`)
      .then(() => {
        const updatedComments = comments.filter((c) => c.id !== comment.id);
        setComments(updatedComments);
      })
      .catch((error) => {
        console.error('Error deleting comment:', error);
      });
  };

  const handleEditComment = (comment) => {
    setEditingComment(comment);
  };

  const handleSaveComment = (editedComment) => {
    // 댓글 수정
    axios
      .patch(`${apiUrl}/recipes/${recipeId}/comment/1`, {
        content: editedComment.content,
      })
      .then(() => {
        setEditingComment(null);

        const updatedComments = comments.map((comment) =>
          comment.id === editedComment.id ? editedComment : comment,
        );
        setComments(updatedComments);
      })
      .catch((error) => {
        console.error('Error updating comment:', error);
      });
  };

  const handleCommentChange = (e) => {
    setNewComment(e.target.value);
  };

  const handleCommentSubmit = () => {
    //댓글 작성
    if (newComment.trim() !== '') {
      axios
        .post(`${apiUrl}/recipes/${recipeId}/comment`, {
          content: newComment,
        })
        .then((response) => {
          setComments([...comments, response.data]);
          setNewComment('');
        })
        .catch((error) => {
          console.error('Error creating comment:', error);
        });
    }
  };

  const headers = {
    'ngrok-skip-browser-warning': '69420',
    'Content-Type': 'application/json',
  };

  useEffect(() => {
    const apiUrl = `https://33fe-45-64-145-74.ngrok-free.app/recipes/main/RCP_NM?RCP_NM=${recipe_nm}`;

    axios
      .get(apiUrl, { headers })
      .then((response) => {
        const recipeData = response.data;
        setRecipe(recipeData);
        console.log(recipeData);
        console.log('에러없음');
      })
      .catch((error) => {
        console.error('Error fetching recipe:', error);
      });
  }, [recipeId]);

  const handleDeleteRecipe = () => {
    axios
      .delete(apiUrl)
      .then((response) => {
        console.log('Recipe deleted successfully');
        setRecipe({});
        navigate('/recipes');
      })
      .catch((error) => {
        console.error('Error deleting recipe:', error);
      });
  };

  if (!recipe) {
    return (
      <Loadingtext>
        <div className="loading-container">
          <div className="loading-icon">⌛</div>
          <div className="loading-text">Loading...</div>
        </div>
      </Loadingtext>
    );
  }
  //찜 등록
  const handleToggleLike = () => {
    if (!isLiked) {
      axios
        .post(`${apiUrl}/recipes`)
        .then(() => {
          setIsLiked(true);
        })
        .catch((error) => {
          console.error('Error liking recipe:', error);
        });
    } else {
      axios
        .delete(`${apiUrl}/recipes/${recipeId}`)
        .then(() => {
          setIsLiked(false);
        })
        .catch((error) => {
          console.error('Error unliking recipe:', error);
        });
    }
  };

  return (
    <>
      {/* <Header /> */}
      <RecipeInfo>
        <div className="recipe-name">
          {recipe[0].rcp_NM} : {recipe[0].rcp_PAT2}
        </div>
        <div className="section1">
          <div>
            <div className="main-img">
              <img src={recipe[0].att_FILE_NO_MAIN} alt={recipe[0].RCP_NM} />
            </div>
            <div className="hashtag">#{recipe[0].hash_TAG}</div>
          </div>
        </div>
        <div className="section3">
          <div
            className="heart"
            role="button"
            onClick={handleToggleLike}
            onKeyDown={(event) => {
              if (event.key === 'Enter' || event.key === ' ') {
                handleToggleLike();
              }
            }}
            tabIndex="0"
            aria-label={isLiked ? 'Unlike' : 'Like'}
          >
            <span>레시피 찜하기</span>
            <p>{isLiked ? '❤️' : '🤍'}</p>
          </div>
          <div className="side-info">
            <div className="nutTitle">재료</div>
            <div className="nutrient">
              <div> - {recipe[0].rcp_PARTS_DTLS}</div>
              <div> 1. 열량 : {recipe[0].info_ENG}g</div>
              <div> 2. 탄수화물 : {recipe[0].info_CAR}g</div>
              <div> 3. 단백질 : {recipe[0].info_PRO}g</div>
              <div> 4. 지방 : {recipe[0].info_FAT}g</div>
              <div> 5. 나트륨 : {recipe[0].info_NA}g</div>
            </div>
          </div>
        </div>
        <div className="section2">
          <div className="title">조리방법</div>
          <div className="plan">
            <ul>
              {Array.from({ length: 20 }, (_, i) => i + 1).map((index) => (
                <li key={`manual-${index}`}>
                  {recipe[0] &&
                  recipe[0][`manual${index.toString().padStart(2, '0')}`]
                    ? `${index}. ${recipe[0][
                        `manual${index.toString().padStart(2, '0')}`
                      ].slice(2)}`
                    : null}
                </li>
              ))}
            </ul>
          </div>
        </div>
      </RecipeInfo>
      <Editbtn>
        <div
          className="edit"
          onClick={handleEditButtonClick}
          role="button"
          tabIndex={0}
          onKeyDown={(event) => {
            if (event.key === 'Enter' || event.key === ' ') {
              handleEditButtonClick();
            }
          }}
        >
          레시피 수정
        </div>
        <div
          className="delete"
          onClick={handleDeleteRecipe}
          role="button"
          tabIndex={0}
          onKeyDown={(event) => {
            if (event.key === 'Enter' || event.key === ' ') {
              handleDeleteRecipe();
            }
          }}
        >
          레시피 삭제
        </div>
      </Editbtn>
      <CommentList>
        <div className="comment-title">댓글 작성</div>
        <div className="create-comment">
          <textarea
            value={newComment}
            onChange={handleCommentChange}
            placeholder="댓글을 작성해주세요."
          />
          <button onClick={handleCommentSubmit}>댓글 작성</button>
        </div>
        <div className="comment-section">
          {comments.map((comment, index) => (
            <div className="comment-box" key={comment.id}>
              {editingComment === comment ? (
                <div>
                  <textarea
                    value={comment.content}
                    onChange={(e) =>
                      handleSaveComment({
                        ...comment,
                        content: e.target.value,
                      })
                    }
                  />
                  <button onClick={() => handleSaveComment(comment)}>
                    저장
                  </button>
                  <button onClick={() => handleDeleteComment(comment)}>
                    삭제
                  </button>{' '}
                </div>
              ) : (
                <div>
                  <p>{comment.content}</p>
                  <button onClick={() => handleEditComment(comment)}>
                    수정
                  </button>
                  <button onClick={() => handleDeleteComment(comment)}>
                    삭제
                  </button>{' '}
                </div>
              )}
            </div>
          ))}
        </div>
      </CommentList>
    </>
  );
};

export default RecipeDes;
