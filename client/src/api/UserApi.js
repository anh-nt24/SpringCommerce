import axios from "axios";

export const UserLogin = async (form) => {
    const config = {
        method: "POST",
        url: 'http://localhost:8080/login',
        data:{
            'username': form.username,
            'password': form.password
        },
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    }
    const { data } = await axios(config)
    return data
}

export const UserSignUp = async (form) => {
    const config = {
        method: 'POST',
        url: 'http://localhost:8080/signup',
        data: form,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }
    const { data } = await axios(config)
    return  data
}

export const GetUserInfo = async (id) => {
    const { data } = await axios.get('http://localhost:8080/getinfo', {
        params: {id}
    })
    return data
}