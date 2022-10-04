import request from "../utils/request";
import {Login} from "../types/login";
import {Register} from "../types/register";

export function login(email: string, password: string) {
    const data: Login = {
        email, password
    }
    return request({
        url: '/api/login',
        headers: { isToken:false },
        method: 'post',
        data
    })
}

export function logout() {
    return request({
        url: '/api/logout',
        method: 'post'
    })
}

