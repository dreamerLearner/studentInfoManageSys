<template>
  <div class="container">
    <el-form :model="Manager" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="管理员姓名" prop="name">
        <el-input v-model="Manager.name"></el-input>
      </el-form-item>
      <el-form-item label="管理员账号" prop="account">
        <el-input v-model="Manager.account"></el-input>
      </el-form-item>
      <el-form-item label="管理员密码" prop="password">
        <el-input type="password" v-model="Manager.password"></el-input>
      </el-form-item>
      <el-form-item label="管理员手机" prop="phone">
        <el-input type="text" v-model="Manager.phone"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'VueCliSecondAddstudentform',

  data() {
    return {
      resultMessage: '',
      Manager: {
        name: '',
        account: '',
        password: '',
        phone:''
      },
      rules: {
        name: [
          { required: true, message: '请输入管理员姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
        ],
        account: [{ required: true, message: '请输入管理员账号', trigger: 'blur' }],
        password: [
          { required: true, message: '请输入管理员密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' },
        ],
        phone: [
          { required: true, message: '请输入管理员手机', trigger: 'blur' },
          { min: 11, max: 11, message: '长度在 11 个字符', trigger: 'blur' },
        ],
      },
    }
  },

  mounted() {},

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          var { data } = await this.$axios.post('/manager/addManager', this.Manager)
          if (data.code == 406) {
            // 添加失败
            this.$message({
              message: data.message,
              type: 'warning',
            })
          }
          if (data.code == 200) {
            // 添加成功
            this.$message({
              message: data.message,
              type: 'success',
            })
          }
          //   服务获取失败
          console.log(res.data.code)
          if (res.data.code == 503) {
            this.$message.error(res.data.message)
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
  },
}
</script>

<style scoped>
.container {
  background-color: white;
  padding: 50px;
}
</style>