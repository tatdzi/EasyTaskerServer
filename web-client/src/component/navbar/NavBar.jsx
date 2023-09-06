import React from "react";
import style from "./NavBar.module.css"
import {NavLink} from "react-router-dom";

const NavBar= () =>{
    return(
    <nav className={style.nav}>
        <div>
            <NavLink to='/'>Profile</NavLink>
        </div>
        <div>
            <NavLink to='/projects'>Projects</NavLink>
        </div>
        <div>
            <NavLink to='/tasks'>Tasks</NavLink>
        </div>
        <div>
            <NavLink to='/audit'>Audit</NavLink>
        </div>
        <div>
            <NavLink to='/users'>Users</NavLink>
        </div>
    </nav>
    )
}

export default NavBar