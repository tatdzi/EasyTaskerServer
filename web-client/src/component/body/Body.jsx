import React from "react";
import style from "./Body.module.css"
import StartPage from "../startPage/StartPage";
import MainPage from "../mainPage/MainPage";
import {Route, Routes} from "react-router-dom";

const Body= () =>{
    return(
        <div>
            <Routes>
                <Route path='/auth' element={<StartPage/>}/>
                <Route path='/main' element={<MainPage/>}/>
            </Routes>
        </div>
    )
}
export default Body;