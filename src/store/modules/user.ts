import {defineStore} from "pinia";
import {getToken, removeToken, setToken} from "../../utils/auth";
import {Login} from "../../types/login";
import {login, logout} from "../../api/login";
import {error} from "echarts/types/src/util/log";

const useUserStore = defineStore(
    'user',
    {
        state: () => ({
            token: getToken(),
            name: '',
            roles: [],
            permissions: []
        }),
        actions: {
            login(userInfo: Login) {
                const username = userInfo.email.trim()
                const password = userInfo.password
                return new Promise((resolve, reject) => {
                    login(username, password).then((res:any) => {
                        setToken(res.token)
                        this.token = res.token
                        resolve("登录成功")
                    }).catch(error => reject(error))
                })
            },
            logout() {
                return new Promise((resolve, reject) => {
                    logout().then(() => {
                        this.token = ''
                        removeToken()
                        resolve("注销成功")
                    }).catch(error => reject(error))
                })
            },
            register() {
                const data = {

                }
                return new Promise((resolve, reject) => {

                })
            }
        }
    }
)
export default useUserStore