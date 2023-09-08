import { styled } from 'styled-components';
import PropTypes from 'prop-types';

const SearchDiv = styled.div`
  display: flex;
  justify-content: center;
`;

const SearchInput = styled.input`
  width: 400px;
  height: 30px;
  border: 0;
  border-radius: 10px;
  background-color: rgb(233, 233, 233);
  padding-left: 10px;
`;

function Search({ searchData, HandleOnSearch }) {
  return (
    <SearchDiv>
      <SearchInput
        placeholder="검색 내용을 적어주세요."
        value={searchData}
        onChange={HandleOnSearch}
      />
    </SearchDiv>
  );
}

Search.propTypes = {
  searchData: PropTypes.string.isRequired,
  HandleOnSearch: PropTypes.func.isRequired,
};

export default Search;
