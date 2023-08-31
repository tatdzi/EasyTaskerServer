import axios from "axios";

const instance = axios.create({
    baseURL:"http://localhost/api/v1/"
})

export const authAPI = {
    registration(data){
        return instance.post('users/registration',data)
    },
    me(token){
        return instance.get('users/me',{headers: {
            Authorization : `Bearer ${token}`,
        }})
    },
    login(mail,password){
        return instance.post('users/login',{mail,password})
    }
}
export const usersAPI={
    create(data,token){
        return instance.post('users',data, {headers: {
                Authorization : `Bearer ${token}`,
            }})
    },
    getPage(size,page,token){
        return instance.get(`users?size=${size}&page=${page}`,{headers: {
                Authorization : `Bearer ${token}`,
            }})
    },
    getPageDef(token){
        return instance.get(`users`,{headers: {
                Authorization : `Bearer ${token}`,
            }})
    },
    getCard(uuid,token){
        return instance.get(`users/${uuid}`,{headers: {
                Authorization : `Bearer ${token}`,
            }})
    },
    update(uuid,dt_update,token,data){
        return instance.put(`users/${uuid}/dt_update/${dt_update}`,data,{headers: {
                Authorization : `Bearer ${token}`,
            }})
    }

}