import { configureStore, createSlice } from '@reduxjs/toolkit';

let sidebarState = createSlice({
  name: 'sidebarState',
  initialState: true,
  reducers: {
    changeSideState(state) {
      return !state;
    },
  },
});

export let { changeSideState } = sidebarState.actions;

export default configureStore({
  reducer: {
    sideState: sidebarState.reducer,
  },
});
