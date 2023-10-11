import React from "react";
import style from "./MainPage.module.css"
import { Route, Routes} from "react-router-dom";
import NavBar from "../navbar/NavBar";
import Login from "../login/Login";
import Profile from "../profile/Profile";
import Projects from "../projects/Projects";
import Tasks from "../tasks/Tasks";
import Audit from "../audit/Audit";
import Users from "../users/Users";

const MainPage= () =>{
    return(
        <div className={style.area}>
        <NavBar/>
            <div className={style.switch}>
                <Routes>
                    <Route path='/' element={<Profile/>}/>
                    <Route path='/projects' element={<Projects/>}/>
                    <Route path='/tasks' element={<Tasks/>}/>
                    <Route path='/audit' element={<Audit/>}/>
                    <Route path='/users' element={<Users/>}/>
                </Routes>
        </div>
</div>
    )
}
export default MainPage;