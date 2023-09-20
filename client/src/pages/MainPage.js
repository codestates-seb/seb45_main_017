import { useState, useEffect, useMemo } from 'react';
import { styled } from 'styled-components';
import axios from 'axios';
import Category from '../components/MainPageCompo/Category';
import MainList from '../components/MainPageCompo/MainList';
import Paging from '../components/MainPageCompo/Paging';
import Search from '../components/MainPageCompo/Search';
import Header from '../components/common/Header';
import { useParams } from 'react-router-dom';

const PageContainer = styled.div``;
const PageDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 40px;
`;
const PageP = styled.p`
  font-weight: bolder;
  width: 250px;
  height: 25px;
  margin-right: 1100px;
  white-space: nowrap;
`;
const LargeText = styled.span`
  font-size: 50px;
  font-weight: bold;
  color: skyblue;
`;

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

  const start =
    obj.page === undefined ? 0 : obj.page === 'page' ? 0 : (obj.page - 1) * 12;
  const end = start + 12;

  const menu = useMemo(
    () => [
      { id: 1, name: '전체', content: 'all', picture: 'picture/food.png' },
      { id: 2, name: '밥', content: 'rice', picture: 'picture/rice.png' },
      { id: 3, name: '반찬', content: 'sidedish', picture: 'picture/side.png' },
      { id: 4, name: '국&찌개', content: 'soup', picture: 'picture/soup.png' },
      {
        id: 5,
        name: '일품',
        content: 'maindish',
        picture: 'picture/noodle.png',
      },
      {
        id: 6,
        name: '후식',
        content: 'dessert',
        picture: 'picture/dessert.png',
      },
    ],
    [],
  );

  const config = {
    headers: {
      'Content-Type': `application/json`,
      'ngrok-skip-browser-warning': '69420',
    },
  };

  const fetchData = async () => {
    try {
      const response = await axios.get(
        `https://619b-45-64-144-244.ngrok-free.app/recipes/main`,
      );
      setData(response.data);
      console.log(data);
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
        Object.values(data).filter((el) => {
          return el.rcp_PAT2 === obj.name;
        }),
      );
    }
  }, [data, obj.name]);

  //검색으로 인한 분류
  const HandleOnSearch = (e) => {
    const typing = e.target.value.toLowerCase();
    setSearchData(typing);

    setFilterData(
      Object.values(data).filter((el) => {
        return obj.name === '전체'
          ? el.rcp_NM.toLowerCase().includes(typing)
          : el.rcp_NM.toLowerCase().includes(typing) &&
              el.rcp_PAT2 === obj.name;
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
      <Header />
      <Search searchData={searchData} HandleOnSearch={HandleOnSearch} />
      <Category menu={menu} obj={obj} setSearchData={setSearchData}></Category>
      <PageDiv>
        <PageP>
          총 <LargeText>{filterData.length}</LargeText> 개의 맛있는 레시피가
          있습니다.
        </PageP>
      </PageDiv>
      <MainList currentData={currentData}></MainList>
      <Paging filterData={filterData} obj={obj} />
    </PageContainer>
  );
}

export default MainPage;
