import React from "react";
import style from "./Header.module.css"

const Header = () =>{
    return(
        <header className={style.header}>
            <div>
                Task Manager
            </div>
        </header>
    )
}
export default Header;