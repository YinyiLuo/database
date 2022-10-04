import request from "../utils/request";
import {Register} from "../types/register";

export function getCode(email: string, mark: string) {
    return request({
        url: '/api/code',
        headers: {
            isToken: false
        },
        method: 'post',
        data: { email, mark }
    })
}

export function register(data: Register) {
    return request({
        url: '/api/register',
        headers: {
            isToken: false
        },
        method: 'post',
        data
    })
}