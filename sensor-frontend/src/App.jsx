import React from "react";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NavBar from "./components/NavBar";
import AddSensor from "./components/AddSensor";
import Main from "./components/Main";

function App() {

  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/sensor" element={<AddSensor />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
