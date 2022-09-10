<template>
  <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
    <h3 class="login-title">欢迎登录</h3>
    <el-form-item label="账号" prop="account">
      <el-input type="text" placeholder="请输入账号" v-model="form.account" />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" placeholder="请输入密码" v-model="form.password" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" :plain="true" v-on:click="onSubmit('loginForm')">登录</el-button>
    </el-form-item>
    <router-link to="/loginWithPhone">
      <div class="loginwithphone">使用管理员手机号登录</div>
    </router-link>
  </el-form>
</template>

<script>
export default {
  name: 'VueCliSecondLoginWithPwd',

  data() {
    return {
      form: {
        account: '',
        password: '',
      },

      rules: {
        account: [{ required: true, message: '账号不可为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不可为空', trigger: 'blur' }],
      },

      dialogVisible: false,
      resultMessage: '',
    }
  },

  mounted() {},

  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post('/manager/loginWithPwd', {
              account: this.form.account,
              password: this.form.password,
            })
            .then((res) => {
              // 请求失败
              if (res.data.code == 406) {
                this.resultMessage = res.data.message
                this.$message.error(this.resultMessage)
              }
              if (res.data.code == 200) {
                // 请求成功
                console.log(res.data)
                this.resultMessage = res.data.message
                this.$message({
                  message: this.resultMessage,
                  type: 'success',
                })
                // 设置token值
                this.$store.commit('set_token', res.data.data[1])
                this.$router.push({ path: '/Container', query: { managerName: res.data.data[0] } })
              }
              //   服务获取失败
              console.log(res.data.code)
              if (res.data.code == 503) {
                this.resultMessage = res.data.message
                this.$message.error(this.resultMessage)
              }
            })
            .catch((res) => {
              this.$message.error('页面出错，请联系管理员')
            })
        } else {
          this.dialogVisible = true
          return false
        }
      })
    },
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