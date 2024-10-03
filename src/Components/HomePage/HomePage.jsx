import { Grid } from '@mui/material';  // Sửa lỗi cú pháp trong import
import React from 'react';
import Navigation from '../Navigation/Navigation';
import { Home } from '@mui/icons-material';
import HomeSection from '../HomeSection/HomeSection';
const HomePage = () => {
  return (
    <Grid container xs={12} className='px-5 lg:px-36 justify-between'>  {/* Sửa lỗi '1g' thành 'lg' */}
      
      {/* Navigation part */}
      <Grid item xs={0} lg={2.5} className='hidden lg:block w-full relative'>
        <Navigation />  {/* Sửa lỗi gọi component Navigation */}
      </Grid>

      {/* Middle part */}
      <Grid item xs={12} lg={6} className='hidden lg:block w-full relative'>
    <HomeSection /> {/* Thêm dấu đóng cho HomeSection */}
      </Grid>

      {/* Right part */}
      <Grid item xs={0} lg={3} className='hidden lg:block w-full relative'>
        <p className='text-center'>right part</p>  {/* Sửa lỗi cú pháp */}
      </Grid>

    </Grid>
  );
};

export default HomePage;
