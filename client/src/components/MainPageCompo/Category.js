import { styled } from 'styled-components';
import PropTypes from 'prop-types';
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
function Category({ onSwitch, menu, setCurrentPage }) {
  const HandleClickIndex = (index) => {
    onSwitch(index);
    setCurrentPage(1);
  };
  return (
    <CategoryContainer>
      <CategoryUl>
        {menu.map((el, index) => {
          return (
            <CategoryLi key={index}>
              <CaButton onClick={() => HandleClickIndex(index)}>
                {el.name}
              </CaButton>
            </CategoryLi>
          );
        })}
      </CategoryUl>
    </CategoryContainer>
  );
}

Category.propTypes = {
  onSwitch: PropTypes.func.isRequired,
  menu: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      content: PropTypes.string.isRequired,
    }),
  ).isRequired,
  setCurrentPage: PropTypes.func.isRequired,
};

export default Category;
