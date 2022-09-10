<template>
  <div class="container">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="课程名" prop="CourseName">
        <el-input v-model="ruleForm.CourseName"></el-input>
      </el-form-item>
      <el-form-item label="课程学时" prop="peirodTime">
        <el-input v-model="ruleForm.peirodTime"></el-input>
      </el-form-item>
      <el-form-item label="授课老师" prop="teacherName">
        <el-select v-model="ruleForm.teacherName" placeholder="请选择授课老师">
          <el-option label="张三" value="张三"></el-option>
          <el-option label="李四" value="李四"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程类型" prop="CourseType">
        <el-select v-model="ruleForm.CourseType" placeholder="请选择课程类型">
          <el-option label="公选课" value="公选课"></el-option>
          <el-option label="选修课" value="选修课"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="教学时间" required>
        <el-col :span="11">
          <el-form-item prop="date1">
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="date2">
            <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%"></el-time-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="授课地点" prop="address">
        <el-select v-model="ruleForm.address" placeholder="请选择授课地点">
          <el-option label="一栋" value="一栋"></el-option>
          <el-option label="二栋" value="二栋"></el-option>
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
      ruleForm: {
        CouseName: '',
        peirodTime: '',
        teacherName: '',
        CourseType: '',
        date1: '',
        date2: '',
        address: '',
      },
      rules: {
        CourseName: [{ required: true, message: '请输入课程名', trigger: 'blur' }],
        peirodTime: [{ required: true, message: '请输入学时', trigger: 'blur' }],
        teacherName: [{ required: true, message: '请输入教师姓名', trigger: 'change' }],
        CourseType: [{ required: true, message: '请选择课程名称', trigger: 'change' }],
        date1: [{ type: 'date', required: true, message: '请选择日期', trigger: 'change' }],
        date2: [{ type: 'date', required: true, message: '请选择时间', trigger: 'change' }],
        address: [{ required: true, message: '请选择授课地点', trigger: 'change' }],
      },
    }
  },

  mounted() {},

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
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