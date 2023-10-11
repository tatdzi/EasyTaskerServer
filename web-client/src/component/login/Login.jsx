import React from "react";
import style from "./Login.module.css"
import {Field, reduxForm} from "redux-form";
import {connect} from "react-redux";
import {loginThunk} from "../../redux/AuthReduser";
import {Navigate} from "react-router-dom";
const LoginForm = (props) =>{
    return(
        <form onSubmit={props.handleSubmit}>
            <div>
                <Field placeholder={"mail"} name={"mail"} component={"input"}/>
            </div>
            <div>
                <Field placeholder={"password"} type={"password"} name={"password"} component={"input"}/>
            </div>
            <div>
                <button>login</button>
            </div>
        </form>
    )
}

const LoginReduxForm = reduxForm({form: 'login'})(LoginForm)


const Login= (props) =>{
    const onSubmit = (formData) =>{
        props.loginThunk(formData.mail,formData.password);
    }
    if (props.token != null){
        return (<Navigate to={'/profile'} />)
    }
    return(
        <div className={style.login}>
            <h1>Login</h1>
            <LoginReduxForm onSubmit={onSubmit}/>
        </div>
    )
}
const mapStateToProps = (state) =>({
    token: state.authDate.token
})

export default connect(mapStateToProps,{loginThunk}) (Login)