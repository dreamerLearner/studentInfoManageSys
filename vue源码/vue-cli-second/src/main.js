import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store/store.js'
import instance from './http/newAjax'
import './localStorage/localStorage'

Vue.prototype.$axios = instance
Vue.use(ElementUI);
Vue.config.productionTip = false
new Vue({
  render: h => h(App),
  router:router,
  store:store
}).$mount('#app')
