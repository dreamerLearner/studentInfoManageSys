<template>
  <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
    <h3 class="login-title">欢迎登录</h3>
    <el-form-item label="手机号" prop="phone">
      <el-input type="text" placeholder="请输入手机号" v-model="form.phone" />
    </el-form-item>
    <el-form-item label="验证码" prop="code">
      <el-input type="code" style="width: 50%; margin-right: 20px" placeholder="请输入验证码" v-model="form.code" />
      <el-button type="primary" :plain="true" v-on:click="getCode('loginForm')">获取验证码</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" :plain="true" v-on:click="onSubmit('loginForm')">登录</el-button>
    </el-form-item>
    <router-link to="/loginWithPwd">
      <div class="loginwithPwd">使用管理员密码登录</div>
    </router-link>
  </el-form>
</template>

<script>
export default {
  name: 'VueCliSecondLoginWithPhone',

  data() {
    return {
      form: {
        phone: '',
        code: '',
      },

      rules: {
        phone: [{ required: true, message: '手机号不可为空', trigger: 'blur' }],
        code: [{ required: true, message: '验证码不可为空', trigger: 'blur' }],
      },

      dialogVisible: false,
      resultMessage: '',
    }
  },

  mounted() {},

  methods: {
    getCode(formName) {
      this.$refs[formName].validateField('phone')
      this.$axios
        .get('/manager/sendCode', {
          params: {
            phone: this.form.phone,
          },
        })
        .then((res) => {
          // 请求失败
          if (res.data.code == 406) {
            this.resultMessage = res.data.message
            this.$message.error(this.resultMessage)
          }
          if (res.data.code == 200) {
            // 请求成功
            this.resultMessage = res.data.message
            this.$message({
              message: this.resultMessage,
              type: 'success',
            })
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
    },
    onSubmit(formName) {
      console.log(this.$refs[formName])
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post('/manager/loginWithPhone', {
              phone: this.form.phone,
              code: this.form.code,
            })
            .then((res) => {
              // 请求失败
              if ((res.data.code == 406) | (res.data.code == 404)) {
                this.resultMessage = res.data.message
                this.$message.error(this.resultMessage)
              }
              if (res.data.code == 200) {
                // 请求成功
                this.resultMessage = res.data.message
                this.$message({
                  message: this.resultMessage,
                  type: 'success',
                })
                // 设置token值
                console.log('token:' + res.data.data[1])
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
.loginwithPwd {
  position: relative;
  text-align: right;
  font-size: 10px;
}
</style>
