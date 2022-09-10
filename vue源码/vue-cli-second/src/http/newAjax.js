import axios from 'axios'
import store from '../store/store'
import router from '../router/index.js'
var instance = axios.create({ timeout: 1000 * 12 })
instance.defaults.baseURL = 'http://localhost:8081/student-service'
// instance.defaults.withCredentials = true
instance.interceptors.request.use(function (config) {
    // console.log("token:"+localStorage.getItem('token'))
    const token = localStorage.getItem("token")
    if (token) {
        config.headers.Authorization = token
    }
    return config
})
instance.interceptors.response.use(function (response) {
    // 刷新token时间
    const token = response.config.headers.Authorization
    if (token) {
        store.commit('set_token', token)
    }
    return response
},err=>{
    router.push({path:"/",query:{Msg:'连接超时'}})
})

export default instance