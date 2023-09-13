import { styled } from 'styled-components';
import PropTypes from 'prop-types';
import FoodData from './FoodData';

const Container = styled.div`
  display: flex;
  justify-content: center;
`;

const ListContainer = styled.ul`
  display: grid;
  grid-template-columns: repeat(4, 350px);
  justify-content: center;
  list-style: none;
  padding-top: 10px;
`;

// currentData 값을 출력
function MainList({ currentData }) {
  return (
    <Container>
      <ListContainer>
        {Object.values(currentData).map((el) => {
          return (
            <FoodData
              key={el.RCP_SEQ}
              picture={el.ATT_FILE_NO_MAIN}
              title={el.RCP_NM}
            ></FoodData>
          );
        })}
      </ListContainer>
    </Container>
  );
}

MainList.propTypes = {
  currentData: PropTypes.array.isRequired,
};

export default MainList;
