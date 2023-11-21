import axios from "axios";

export const UserLogin = async (form) => {
    const config = {
        method: "POST",
        url: 'http://localhost:8080/api/account/login',
        data: {
            'email': form.email,
            'password': form.password
        },
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    }
    return await axios(config);
}

export const UserSignUp = async (form) => {
    const config = {
        method: 'POST',
        url: 'http://localhost:8080/api/account/register',
        data: form,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }
    return await axios(config)
}