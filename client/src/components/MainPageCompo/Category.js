import { styled } from 'styled-components';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

const CategoryContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const CategoryUl = styled.ul`
  display: flex;
  flex-direction: row;
  list-style-type: none;
`;

const CategoryLi = styled.li`
  margin-right: 20px;
`;

const CaButton = styled.button`
  width: 70px;
  height: 70px;

  border-radius: 100px;
  cursor: pointer;
  font-size: 14px;
  font-family: sans-serif;
  font-weight: bolder;
  color: white;
  background-color: #569aff;
  &:hover {
    background-color: rgba(0, 109, 205, 1);
  }
`;

// 카테고리
function Category({ menu, obj, setSearchData }) {
  const HandleClickIndex = (index) => {
    obj.name = menu[index].name;
    setSearchData('');
  };

  return (
    <CategoryContainer>
      <CategoryUl>
        {menu.map((el, index) => {
          return (
            <CategoryLi key={index} value={menu[index].name}>
              <Link to={`/recipes/${menu[index].name}/${1}`}>
                <CaButton onClick={() => HandleClickIndex(index)}>
                  {el.name}
                </CaButton>
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
