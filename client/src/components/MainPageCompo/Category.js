import { styled } from 'styled-components';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { useState } from 'react';

const CategoryContainer = styled.div`
  display: flex;
  justify-content: center;
  margin: 30px 0px 10px 0px;
`;

const CategoryUl = styled.ul`
  display: flex;
  flex-direction: row;
  list-style-type: none;
`;

const CategoryLi = styled.li`
  display: flex;
  flex-direction: column;
  padding: 10px;
  margin-right: 20px;

  background-color: ${(props) => (props.active ? 'skyblue' : '')};
`;

const LiBox = styled.div`
  display: flex;
  justify-content: center;
`;

const CaImg = styled.img`
  width: 100px;
  height: 100px;
  border-radius: 100px;
  cursor: pointer;
`;

const CaH4 = styled.h4`
  text-decoration-line: none;
  font-size: 14px;
  font-family: sans-serif;
  font-weight: bolder;
  color: black;
`;

// 카테고리
function Category({ menu, obj, setSearchData }) {
  const [color, setColor] = useState();

  const HandleClickIndex = (index) => {
    obj.name = menu[index].name;
    setSearchData('');
    setColor(index);
  };

  return (
    <CategoryContainer>
      <CategoryUl>
        {menu.map((el, index) => {
          return (
            <CategoryLi
              key={index}
              value={menu[index].name}
              active={color === index}
              onClick={() => HandleClickIndex(index)}
            >
              <Link to={`/recipes/${menu[index].name}/${1}`}>
                <LiBox>
                  <CaImg key={el.id} src={el.picture} alt={`image ${el.id}`} />
                </LiBox>
                <LiBox>
                  <CaH4>{el.name}</CaH4>
                </LiBox>
              </Link>
            </CategoryLi>
          );
        })}
      </CategoryUl>
    </CategoryContainer>
  );
}

Category.propTypes = {
  menu: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      content: PropTypes.string.isRequired,
    }),
  ).isRequired,
  obj: PropTypes.object.isRequired,
  setSearchData: PropTypes.func.isRequired,
};

export default Category;
