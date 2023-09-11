// import { useState, useEffect, useMemo } from 'react';
// import { styled } from 'styled-components';
// import axios from 'axios';
// import Category from '../components/MainPageCompo/Category';
// import MainList from '../components/MainPageCompo/MainList';
// import Paging from '../components/MainPageCompo/Paging';
// import Search from '../components/MainPageCompo/Search';

// const PageContainer = styled.div``;

// function MainPage() {
//   const [currentTab, setCurrenTab] = useState(0); // 카테고리 인덱스
//   const [data, setData] = useState([]); // API로 받아온 모든 데이터
//   const [filterData, setFilterData] = useState([]); // 카테고리 변경으로 인한 data 변경 값
//   const [currentPage, setCurrentPage] = useState(1); // 페이지
//   const [currentData, setCurrentData] = useState([]); // 페이지 변경으로 인한 fillterData 변경 값
//   const [searchData, setSearchData] = useState('');

//   const start = (currentPage - 1) * 12;
//   const end = start + 12;

//   const menu = useMemo(
//     () => [
//       { name: '전체', content: 'all' },
//       { name: '밥', content: 'rice' },
//       { name: '반찬', content: 'sidedish' },
//       { name: '국&찌개', content: 'soup' },
//       { name: '일품', content: 'maindish' },
//       { name: '후식', content: 'all' },
//     ],
//     [],
//   );

//   // data 받아오기
//   const fetchData = async () => {
//     try {
//       const response = await axios.get(
//         `https://255a-119-69-252-33.ngrok-free.app/`,
//       );
//       setData(response.data);
//       console.log(response);
//     } catch (error) {
//       console.error('Error fetching data from the API:', error);
//     }
//   };

//   useEffect(() => {
//     fetchData();
//   }, []);

//   // 카테고리 선택
//   const onSwitch = (index) => {
//     setCurrenTab(index);
//   };

//   // 카테고리로 인한 data 분류
//   useEffect(() => {
//     if (currentTab === 0) {
//       setFilterData(data);
//     } else {
//       setFilterData(
//         Object.values(data).filter((el) => {
//           return el.recipeType === menu[currentTab].name;
//         }),
//       );
//     }
//   }, [currentTab, data, menu]);

//   //검색으로 인한 분류
//   const HandleOnSearch = (e) => {
//     const typing = e.target.value.toLowerCase();
//     setSearchData(typing);

//     setFilterData(
//       Object.values(data).filter((el) => {
//         return currentTab === 0
//           ? el.recipeTitle.toLowerCase().includes(typing)
//           : el.recipeTitle.toLowerCase().includes(typing) &&
//               el.recipeType === menu[currentTab].name;
//       }),
//     );
//   };

//   // 페이지 변경으로 인한 fillterData 분류
//   useEffect(() => {
//     setCurrentData(filterData.slice(start, end));
//   }, [filterData, start, end]);

//   return (
//     <PageContainer>
//       <Search searchData={searchData} HandleOnSearch={HandleOnSearch} />
//       {data}
//       <Category
//         onSwitch={onSwitch}
//         menu={menu}
//         setCurrentPage={setCurrentPage}
//       ></Category>
//       <MainList currentData={currentData}></MainList>
//       <Paging
//         filterData={filterData}
//         currentPage={currentPage}
//         setCurrentPage={setCurrentPage}
//       />
//     </PageContainer>
//   );
// }

// export default MainPage;
// import { useState, useEffect, useMemo } from 'react';
// import { styled } from 'styled-components';
// import axios from 'axios';
// import Category from '../components/MainPageCompo/Category';
// import MainList from '../components/MainPageCompo/MainList';
// import Paging from '../components/MainPageCompo/Paging';
// import Search from '../components/MainPageCompo/Search';

// const PageContainer = styled.div``;

// function MainPage() {
//   const [currentTab, setCurrenTab] = useState(0); // 카테고리 인덱스
//   const [data, setData] = useState([]); // API로 받아온 모든 데이터
//   const [filterData, setFilterData] = useState([]); // 카테고리 변경으로 인한 data 변경 값
//   const [currentPage, setCurrentPage] = useState(1); // 페이지
//   const [currentData, setCurrentData] = useState([]); // 페이지 변경으로 인한 fillterData 변경 값
//   const [searchData, setSearchData] = useState('');

//   const start = (currentPage - 1) * 12;
//   const end = start + 12;

//   const menu = useMemo(
//     () => [
//       { name: '전체', content: 'all' },
//       { name: '밥', content: 'rice' },
//       { name: '반찬', content: 'sidedish' },
//       { name: '국&찌개', content: 'soup' },
//       { name: '일품', content: 'maindish' },
//       { name: '후식', content: 'all' },
//     ],
//     [],
//   );

//   // data 받아오기
//   const fetchData = async () => {
//     try {
//       const response = await axios.get(
//         `https://4c3c-119-69-252-33.ngrok-free.app`,
//       );
//       setData(response.data);
//     } catch (error) {
//       console.error('Error fetching data from the API:', error);
//     }
//   };

//   useEffect(() => {
//     fetchData();
//   }, []);

//   // 카테고리 선택
//   const onSwitch = (index) => {
//     setCurrenTab(index);
//   };

//   // 카테고리로 인한 data 분류
//   useEffect(() => {
//     if (currentTab === 0) {
//       setFilterData(data);
//     } else {
//       setFilterData(
//         Object.values(data).filter((el) => {
//           return el.recipeType === menu[currentTab].name;
//         }),
//       );
//     }
//   }, [currentTab, data, menu]);

//   //검색으로 인한 분류
//   const HandleOnSearch = (e) => {
//     const typing = e.target.value.toLowerCase();
//     setSearchData(typing);

//     setFilterData(
//       Object.values(data).filter((el) => {
//         return currentTab === 0
//           ? el.recipeTitle.toLowerCase().includes(typing)
//           : el.recipeTitle.toLowerCase().includes(typing) &&
//               el.recipeType === menu[currentTab].name;
//       }),
//     );
//   };

//   // 페이지 변경으로 인한 fillterData 분류
//   useEffect(() => {
//     setCurrentData(filterData.slice(start, end));
//   }, [filterData, start, end]);

//   return (
//     <PageContainer>
//       <Search searchData={searchData} HandleOnSearch={HandleOnSearch} />
//       <Category
//         onSwitch={onSwitch}
//         menu={menu}
//         setCurrentPage={setCurrentPage}
//       ></Category>
//       <MainList currentData={currentData}></MainList>
//       <Paging
//         filterData={filterData}
//         currentPage={currentPage}
//         setCurrentPage={setCurrentPage}
//       />
//     </PageContainer>
//   );
// }

// export default MainPage;
// 테스트코드;

import { useState, useEffect, useMemo } from 'react';
import { styled } from 'styled-components';
import axios from 'axios';
import Category from '../components/MainPageCompo/Category';
import MainList from '../components/MainPageCompo/MainList';
import Paging from '../components/MainPageCompo/Paging';
import Search from '../components/MainPageCompo/Search';
import { useParams, useNavigate } from 'react-router-dom';

const PageContainer = styled.div``;

function MainPage() {
  const [currentTab, setCurrenTab] = useState(0); // 카테고리 인덱스
  const [data, setData] = useState([]); // API로 받아온 모든 데이터
  const [filterData, setFilterData] = useState([]); // 카테고리 변경으로 인한 data 변경 값
  const [currentPage, setCurrentPage] = useState(1); // 페이지
  const [currentData, setCurrentData] = useState([]); // 페이지 변경으로 인한 fillterData 변경 값
  const [searchData, setSearchData] = useState('');

  const start = (currentPage - 1) * 12;
  const end = start + 12;

  const navigate = useNavigate();

  const menu = useMemo(
    () => [
      { name: '전체', content: 'all' },
      { name: '밥', content: 'rice' },
      { name: '반찬', content: 'sidedish' },
      { name: '국&찌개', content: 'soup' },
      { name: '일품', content: 'maindish' },
      { name: '후식', content: 'dessert' },
    ],
    [],
  );

  const { contents = menu[currentTab].content } = useParams();

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

  // 카테고리 선택
  const onSwitch = (index) => {
    setCurrenTab(index);
  };

  useEffect(() => {
    navigate(`/recipes/${contents}/page${currentPage}`);
  }, [contents, currentPage, navigate]);

  // 카테고리로 인한 data 분류
  useEffect(() => {
    if (currentTab === 0) {
      setFilterData(data);
    } else {
      setFilterData(
        data.filter((el) => {
          return el.RCP_PAT2 === menu[currentTab].name;
        }),
      );
    }
  }, [currentTab, data, menu]);

  //검색으로 인한 분류
  const HandleOnSearch = (e) => {
    const typing = e.target.value.toLowerCase();
    setSearchData(typing);

    setFilterData(
      data.filter((el) => {
        return currentTab === 0
          ? el.RCP_NM.toLowerCase().includes(typing)
          : el.RCP_NM.toLowerCase().includes(typing) &&
              el.RCP_PAT2 === menu[currentTab].name;
      }),
    );
  };

  // 페이지 변경으로 인한 fillterData 분류
  useEffect(() => {
    setCurrentData(filterData.slice(start, end));
  }, [filterData, start, end]);

  return (
    <PageContainer>
      <Search searchData={searchData} HandleOnSearch={HandleOnSearch} />
      <Category
        onSwitch={onSwitch}
        menu={menu}
        setCurrentPage={setCurrentPage}
      ></Category>
      <MainList currentData={currentData}></MainList>
      <Paging
        filterData={filterData}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
        navigate={navigate}
        contents={contents}
      />
    </PageContainer>
  );
}

export default MainPage;
