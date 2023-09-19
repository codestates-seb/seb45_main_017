import PropTypes from 'prop-types';
import { styled } from 'styled-components';
import { Link } from 'react-router-dom';

const ListLi = styled.li``;

const Picture = styled.img`
  width: 300px;
  height: 300px;
  border-radius: 10px;
`;

const FoodName = styled.h3`
  width: 300px;
  font-size: 15px;
  font-weight: bold;
  margin: 5px 0px 20px 3px;
`;

// 리스트에 나오는 한칸의 내용
function FoodData({ picture, title, id }) {
  return (
    <ListLi>
      <Link to={`/recipe-des/${id}`}>
        <Picture src={picture} alt="product picture" />
      </Link>
      <FoodName>{title}</FoodName>
    </ListLi>
  );
}

FoodData.propTypes = {
  picture: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  id: PropTypes.string.isRequired,
};
export default FoodData;
