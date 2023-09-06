import {applyMiddleware, combineReducers, legacy_createStore} from "redux";
import authReduser from "./AuthReduser";
import thunkMiddleware from "redux-thunk";
import {reducer as formReduser} from 'redux-form';
import userReduser from "./UserReduser";

let redusers = combineReducers({
    authDate:authReduser,
    userData:userReduser,
    form: formReduser
    }

)

let store = legacy_createStore(redusers, applyMiddleware(thunkMiddleware));

export default store
window.store = store