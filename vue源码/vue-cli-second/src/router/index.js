import Vue from 'vue'
import VueRouter from 'vue-router'

import login from '@/components/login.vue'
import loginWithPhone from '@/components/login/loginWithPhone.vue'
import loginWithPwd from '@/components/login/loginWithPwd.vue'
import Container from '@/components/Container.vue'
import AddStudentForm from '@/components/Student/AddStudentForm.vue'
import StduentTable from '@/components/Student/StudentTable.vue'
import AddLessonForm from '@/components/Lesson/AddLessonForm.vue'
import LessonTable from '@/components/Lesson/LessonTable.vue'
import AddManager from '@/components/manager/addManager.vue'

Vue.use(VueRouter)



const vueRouter = new VueRouter({
    routes: [
        { path: '/', component: login, redirect: '/login' },
        {
            path: '/login',
            component: login,
            redirect: '/loginWithPwd',
            children: [
                { path: '/loginWithPhone', component: loginWithPhone },
                { path: '/loginWithPwd', component: loginWithPwd, },
            ]
        },

        {
            path: '/Container',
            component: Container,
            children: [
                { path: '/AddStudentForm', component: AddStudentForm },
                { path: '/StduentTable', component: StduentTable },
                { path: '/AddLessonForm', component: AddLessonForm },
                { path: '/LessonTable', component: LessonTable },
                { path: '/addManager', component: AddManager }
            ]

        },
    ]
})
vueRouter.beforeEach((to, from, next) => {

    let token = localStorage.getItem("token");
    // console.log((to.path != '/loginWithPhone'))
    if ((to.path != '/loginWithPwd') &&
        (to.path != '/loginWithPhone')) {
        console.log("token为" + localStorage.getItem("token"))

        if (token == null) {
            next({ path: '/', query: { errMsg: "连接失效，请登录" } })
        } else {
            next()
        }

    } else {
        next()
    }
})

export default vueRouter