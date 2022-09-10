<template>
  <div class="container">
    <el-form :model="Student" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="学生学号" prop="id">
        <el-input v-model.number="Student.id"></el-input>
      </el-form-item>
      <el-form-item label="学生姓名" prop="name">
        <el-input v-model="Student.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="Student.sex" placeholder="请选择性别">
          <el-option label="男" value="0"></el-option>
          <el-option label="女" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生专业" prop="profession">
        <el-select v-model="Student.profession" placeholder="请选择专业">
          <div v-for="item in Student.SelectProfession" :key="item.id">
            <el-option :label="item.name" :value="item.id"></el-option>
          </div>
        </el-select>
      </el-form-item>
      <el-form-item label="学生院系" prop="department">
        <el-select v-model="Student.department" placeholder="请选择院系">
          <div v-for="item in Student.SelectDepartment" :key="item.id">
            <el-option :label="item.name" :value="item.id"></el-option>
          </div>
        </el-select>
      </el-form-item>
      <el-form-item label="学生年级" prop="grade">
        <el-select v-model="Student.grade" placeholder="请选择年级">
          <el-option label="2019级" value="2019级"></el-option>
          <el-option label="2018级" value="2018级"></el-option>
        </el-select>
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
      Student: {
        id: '',
        name: '',
        sex: '',
        profession: '',
        department: '',
        grade: '',
        SelectProfession: [],
        SelectDepartment: [],
      },
      rules: {
        id: [
          { required: true, message: '请输入学生id', trigger: 'blur' },
          { type: 'integer', message: '请正确输入学生id', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' },
        ],
        sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        profession: [{ required: true, message: '请选择专业名称', trigger: 'change' }],
        department: [{ required: true, message: '请选择院系', trigger: 'change' }],
        grade: [{ required: true, message: '请选择年级', trigger: 'change' }],
      },
    }
  },

  mounted() {},
  async created() {
    var { data } = await this.$axios.post('/FormData/StudentForm')
    var department = data[0]
    var profession = data[1]
    if (profession.code == 406 || department.code == 406) {
      this.resultMessage = '获取信息错误'
      // 获取失败
      this.$message({
        message: this.resultMessage,
        type: 'warning',
      })
    }
    if (department.code == 200 && profession.code == 200) {
      //获取成功
      this.Student.SelectProfession = profession.data
      this.Student.SelectDepartment = department.data
    }
    if (department.code == 200 && profession.code == 503) {
      //获取成功
      this.Student.SelectDepartment = department.data
      this.$message.error(profession.message)
    }
    if (department.code == 503 && profession.code == 200) {
      //获取成功
      this.Student.SelectProfession = profession.data
      this.$message.error(department.message)
    }
    //   服务获取失败
    if (department.code == 503 && profession.code == 503) {
      this.$message.error(profession.message + '       ' + department.message)

    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          var { data } = await this.$axios.post('/student/addStudent', this.Student)
          if (data.code == 406) {
            // 添加失败
            this.resultMessage = data.message
            this.$message({
              message: this.resultMessage,
              type: 'warning',
            })
          }
          if (data.code == 200) {
            // 添加成功
            this.resultMessage = data.message
            this.$message({
              message: this.resultMessage,
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