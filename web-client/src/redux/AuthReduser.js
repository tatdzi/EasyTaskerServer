import {authAPI} from "../api/api";

const SET_TOKEN ="SET_TOKEN"
const SET_ERROR="SET_ERROR"
const SET_STRUCTURED_ERROR="SET_STRUCTURED_ERROR"
const SET_USER="SET_USER"

export let initialState = {
    token: null,
    errors:[],
    structureErrors:[],
    user:{
        uuid: null,
        dt_create: null,
        dt_update: null,
        mail: null,
        fio: null,
        role: null,
        status: null
    }
}
const authReduser = (state=initialState,action) => {
    let state_copy
    switch (action.type) {
        case SET_TOKEN:
            state_copy = {
                ...state,
                token: action.token,
            }
            return state_copy;
        case SET_ERROR:
            state_copy = {
                ...state,
                errors: action.errors,
            }
            return state_copy;
        case SET_STRUCTURED_ERROR:
            state_copy = {
                ...state,
                structureErrors: action.errors,
            }
            return state_copy;
        case SET_USER:
            state_copy = {
                ...state,
                user: action.user,
            }
            return state_copy;
        default:
            return state;
    }
}

export const setToken=(token)=>
    ({type:SET_TOKEN,token:token})

export const setError=(errors)=>
    ({type:SET_ERROR,errors:errors})
export const setStructuredError=(errors)=>
    ({type:SET_STRUCTURED_ERROR,errors:errors})
export const setUserInfo=(user)=>
    ({type:SET_USER,user:user})

export const getInfoAboutMe = (token)=>(dispatch)=>{
    authAPI.me(token).then(response =>{
        dispatch(setUserInfo(response.data))
    })
}

export const loginThunk = (mail,password )=>(dispatch) =>{
    authAPI.login(mail,password)
        .then(response =>{
            console.log(response);
            dispatch(setToken(response.data))
            dispatch(getInfoAboutMe(response.data))
        })
        .catch(error=>{
            console.log(error);
        })
}


export default authReduser