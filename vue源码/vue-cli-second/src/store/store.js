import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);

export default new Vuex.Store({
    state:{
        token:localStorage.getItem('token')
    },
    mutations:{
        set_token(state,token){
            console.log("token为"+token)
            state.token = token
            let curTime=new Date().getTime() + 120000
            localStorage.setItem("token",token,curTime)
        }
    }
})