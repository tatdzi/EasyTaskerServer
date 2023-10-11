import React from "react";
import style from "./StartPage.module.css"
import Login from "../login/Login";
import Info from "../info/Info";

const SratrPage= () =>{
    return(
        <body className={style.area}>
        <Info/>
        <Login/>
        </body>
    )
}
export default SratrPage;