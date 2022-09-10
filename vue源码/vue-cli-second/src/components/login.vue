<template>
  <div class="container" :style="containerStyle">
    <div class="submitForm">
      <router-view/>
    </div>

    <!-- 页面动画部分 -->

    <div class="logo">
      <div class="logoImg">
        <i class="far fa-angry"></i>
        <i class="far fa-tired"></i>
        <i class="far fa-meh"></i>
        <i class="far fa-laugh"></i>
        <i class="far fa-grin-beam"></i>
      </div>
      <div class="logo-star">
        <i class="fas fa-star"></i>
        <i class="fas fa-star"></i>
        <i class="fas fa-star"></i>
        <i class="fas fa-star"></i>
        <i class="fas fa-star"></i>
      </div>
    </div>
    <div class="bubbles">
      <img src="@/img/bubble.png" alt="" />
      <img src="@/img/bubble.png" alt="" />
      <img src="@/img/bubble.png" alt="" />
      <img src="@/img/bubble.png" alt="" />
      <img src="@/img/bubble.png" alt="" />
    </div>
  </div>
</template>

<script>
import loginwithphone from './login/loginWithPhone.vue'
import loginWithPwd from './login/loginWithPwd.vue'
export default {
  name: 'Login',
  data() {
    return {
      containerStyle: {
        backgroundImage: 'url(' + require('@/img/backgroundImg.jpg') + ')',
      }
    }
  },
  mounted(){
    const errMsg = this.$route.query.errMsg
    console.log(this.$route.query.errMsg)
    if(errMsg!=null){
        console.log(errMsg)
        this.$message(errMsg)
    }
  },

  methods: {
  },
  components: {
    loginwithphone,
    loginWithPwd,
  },
}
</script>

<style scoped>
.login-box {
  background-color: white;
  border: 1px solid #dcdfe6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
* {
  padding: 0;
  margin: 0;
}
.container {
  width: 100%;
  height: 100vh;
  background-size: cover;
  background-position: center;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.submitForm {
  z-index: 999;
}
.loginwithphone {
  position: relative;
  text-align: right;
  font-size: 10px;
}
.logo {
  /* opacity: 0.5; */
  /* background-image: linear-gradient(to right top, #84fab0 0%, #8fd3f4 100%); */
  width: 60%;
  height: 50vh;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50px;
}
.logoImg {
  width: 100px;
  height: 100px;
  border-radius: 60%;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateY(-50%);

  overflow: hidden;
  /* transform: translateX(240px); */
}
.logoImg i {
  transition: 0.3s linear;
  transform: translateX(120px);
  margin: 10px;
  font-size: 100px;
  border-radius: 60%;
}
.logo-star {
  width: 100%;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: space-around;
  cursor: pointer;
  bottom: 50px;
}
.logo-star i {
  font-size: 40px;
  width: 40px;
  cursor: pointer;
}
.bubbles {
  width: 100%;
  bottom: -120px;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.bubbles img {
  cursor: pointer;
  z-index: 0;
  pointer-events: none;
  animation: bubble 7s linear infinite;
}

@keyframes bubble {
  0% {
    transform: translateY(0);
    opacity: 0;
  }
  30% {
    opacity: 1;
  }
  70% {
    opacity: 1;
  }
  100% {
    transform: translateY(-80vh);
    opacity: 0;
  }
}
.bubbles img:nth-child(1) {
  animation-delay: 1s;
  width: 50px;
}
.bubbles img:nth-child(2) {
  animation-delay: 5s;
  width: 70px;
}
.bubbles img:nth-child(3) {
  animation-delay: 4.5s;
  width: 100px;
}
.bubbles img:nth-child(4) {
  animation-delay: 2s;
  width: 130px;
}
.bubbles img:nth-child(5) {
  animation-delay: 6s;
  width: 60px;
}
</style>