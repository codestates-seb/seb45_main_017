import { useState, useEffect, useMemo } from 'react';
import { styled } from 'styled-components';
import axios from 'axios';
import Category from '../components/MainPageCompo/Category';
import MainList from '../components/MainPageCompo/MainList';
import Paging from '../components/MainPageCompo/Paging';
import Search from '../components/MainPageCompo/Search';
import { useParams } from 'react-router-dom';

const PageContainer = styled.div``;

function MainPage() {
  const [data, setData] = useState([]); // API로 받아온 모든 데이터
  const [filterData, setFilterData] = useState([]); // 카테고리 변경으로 인한 data 변경 값
  const [currentData, setCurrentData] = useState([]); // 페이지 변경으로 인한 fillterData 변경 값
  const [searchData, setSearchData] = useState(''); // 검색 내용

  //카테고리, 페이지 값 저장
  const obj = useParams();
  console.log(obj);
  console.log(obj.page);
  console.log(currentData);
  console.log(filterData);
  console.log(data);

  const start = obj.page === undefined ? 0 : (obj.page - 1) * 12;
  const end = start + 12;

  const menu = useMemo(
    () => [
      { id: 1, name: '전체', content: 'all' },
      { id: 2, name: '밥', content: 'rice' },
      { id: 3, name: '반찬', content: 'sidedish' },
      { id: 4, name: '국&찌개', content: 'soup' },
      { id: 5, name: '일품', content: 'maindish' },
      { id: 6, name: '후식', content: 'dessert' },
    ],
    [],
  );

  const fetchData = async () => {
    try {
      const response = await axios.get(``);
      setData(response.data.COOKRCP01.row);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);

  // 카테고리로 인한 data 분류
  useEffect(() => {
    if (obj.name === '전체' || obj.name === 'name' || obj.name === undefined) {
      setFilterData(data);
    } else {
      setFilterData(
        data.filter((el) => {
          return el.RCP_PAT2 === obj.name;
        }),
      );
    }
  }, [data, obj.name]);

  //검색으로 인한 분류
  const HandleOnSearch = (e) => {
    const typing = e.target.value.toLowerCase();
    setSearchData(typing);

    setFilterData(
      data.filter((el) => {
        return obj.name === '전체'
          ? el.RCP_NM.toLowerCase().includes(typing)
          : el.RCP_NM.toLowerCase().includes(typing) &&
              el.RCP_PAT2 === obj.name;
      }),
    );
  };

  // 페이지 변경으로 인한 fillterData 분류
  useEffect(() => {
    if (filterData.length > 0) {
      const newCurrentData = filterData.slice(start, end);
      setCurrentData(newCurrentData);
    }
  }, [filterData, start, end]);

  return (
    <PageContainer>
      <Search searchData={searchData} HandleOnSearch={HandleOnSearch} />
      <Category menu={menu} obj={obj} setSearchData={setSearchData}></Category>
      <MainList currentData={currentData}></MainList>
      <Paging filterData={filterData} obj={obj} />
    </PageContainer>
  );
}

export default MainPage;
