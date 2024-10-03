import { Route, Routes } from 'react-router-dom';
import './App.css';  // Sửa dấu nháy thành đúng
import HomePage from './Components/HomePage/HomePage'; 
import Authentication from './Components/Authentication/Authentication';  // Sửa dấu nháy thành đúng

function App() {
  return (
    <div className="">
      <Routes>
        <Route path="/" element={true ? <HomePage /> : <Authentication />} />
      </Routes>
    </div>
  );
}

export default App;
