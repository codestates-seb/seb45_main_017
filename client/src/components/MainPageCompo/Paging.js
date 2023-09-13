import { styled } from 'styled-components';
import PropTypes from 'prop-types';
import Pagination from 'react-js-pagination';
import { useNavigate } from 'react-router-dom';

const PaginationBox = styled.div`
  .pagination {
    display: flex;
    justify-content: center;
    margin: 20px 0px 50px 0px;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  ul.pagination li {
    display: inline-block;
    width: 40px;
    height: 40px;
    border: 1px solid #e2e2e2;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1rem;
  }
  ul.pagination li:first-child {
    border-radius: 5px 0 0 5px;
  }
  ul.pagination li:last-child {
    border-radius: 0 5px 5px 0;
  }
  ul.pagination li a {
    text-decoration: none;
    color: #337ab7;
    font-size: 15px;
  }
  ul.pagination li.active a {
    color: white;
  }
  ul.pagination li.active {
    background-color: #337ab7;
  }
`;

// 페이지네이션을 사용한 페이지 버튼
function Paging({ filterData, obj }) {
  const navigator = useNavigate();

  const handlePageChange = (page) => {
    obj.page = page;
    navigator(`/recipes/${obj.name}/${page}`);
  };
  return (
    <PaginationBox>
      <Pagination
        activePage={obj.page}
        itemsCountPerPage={12}
        totalItemsCount={filterData.length}
        pageRangeDisplayed={5}
        onChange={handlePageChange}
        prevPageText={'‹'}
        nextPageText={'›'}
      />
    </PaginationBox>
  );
}
Paging.propTypes = {
  filterData: PropTypes.array.isRequired,
  obj: PropTypes.object.isRequired,
};
export default Paging;
