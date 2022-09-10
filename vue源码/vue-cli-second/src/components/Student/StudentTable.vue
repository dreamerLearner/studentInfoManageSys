<template>
  <div class="container">
    <!-- 学生信息表 -->
    <el-table :data="StudentTable.filter((data) => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
      <el-table-column label="id" prop="id"></el-table-column>
      <el-table-column label="姓名" prop="name"></el-table-column>
      <el-table-column label="性别" prop="sex"></el-table-column>
      <el-table-column label="专业" prop="profession"></el-table-column>
      <el-table-column label="院系" prop="department"></el-table-column>
      <el-table-column label="年级" prop="grade"></el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input class="inputSearch" v-model="search" size="mini" placeholder="输入关键字搜索" clearable :id="scope.row" />
        </template>
        <template slot-scope="scope">
          <!-- EditForm -->
          <el-button class="editbtn" size="small" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!-- delete -->
          <el-button class="deletebtn" size="small" icon="el-icon-delete" type="danger" @click="deleteStudent(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
      <!-- <el-pagination small layout="prev, pager, next" :total="50"></el-pagination> -->
    </el-table>
    <!-- <el-input v-model="search" placeholder="输入关键字搜索" /> -->
    <el-pagination small layout="prev, pager, next" :total="totalSize * 10" :current-page.sync="current" :page-count="totalSize" @current-change="getStudentPage"></el-pagination>

    <!-- 更新表单 -->
    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <el-form :model="editStudent" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="学生学号">
          <el-input v-model.number="editStudent.id"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名">
          <el-input v-model="editStudent.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="editStudent.sex" placeholder="请选择性别">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生专业">
          <el-select v-model="editStudent.profession" placeholder="请选择专业">
            <div v-for="item in editStudent.SelectProfession" :key="item.id">
              <el-option :label="item.name" :value="item.id"></el-option>
            </div>
          </el-select>
        </el-form-item>
        <el-form-item label="学生院系">
          <el-select v-model="editStudent.department" placeholder="请选择院系">
            <div v-for="item in editStudent.SelectDepartment" :key="item.id">
              <el-option :label="item.name" :value="item.id"></el-option>
            </div>
          </el-select>
        </el-form-item>
        <el-form-item label="学生年级">
          <el-select v-model="editStudent.grade" placeholder="请选择年级">
            <el-option label="2019级" value="2019级"></el-option>
            <el-option label="2018级" value="2018级"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateStudent('ruleForm')">确 定</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'VueCliSecondUsertable',

  data() {
    return {
      //学生表格信息
      StudentTable: [],
      search: '',
      current: 1,
      totalSize: null,
      // 编辑表单信息
      dialogTableVisible: false,
      dialogFormVisible: false,
      editStudent: {
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
          { min: 2, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' },
        ],
        sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        profession: [{ required: true, message: '请选择专业名称', trigger: 'change' }],
        department: [{ required: true, message: '请选择院系', trigger: 'change' }],
        grade: [{ required: true, message: '请选择年级', trigger: 'change' }],
      },
      formLabelWidth: '120px',
    }
  },

  mounted() {},

  created() {
    this.getStudentPage()
  },
  methods: {
    handleEdit(index, row) {
      this.editStudent.id = row.id
      this.editStudent.name = row.name

      this.getStudentForm()
      this.dialogFormVisible = true
    },
    //删除学生信息
    async deleteStudent(index, row) {
      console.log(typeof row.id)
      var { data } = await this.$axios.get('/student/deleteStudent', {
        params: {
          id: row.id,
          name: row.name,
        },
      })
      if (data.code == 200) {
        this.$message({
          message: data.message,
          type: 'success',
        })
      }
      if (data.code == 406) {
        this.$message.error({
          message: data.message,
        })
      }
      //   服务获取失败
      console.log(res.data.code)
      if (res.data.code == 503) {
        this.$message.error(res.data.message)
      }
    },

    //获取学生表单信息
    async getStudentForm() {
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
      if (department.code == 200 || profession.code == 200) {
        //获取成功
        this.editStudent.SelectProfession = profession.data
        this.editStudent.SelectDepartment = department.data
      }
      //   服务获取失败
      console.log(res.data.code)
      if (res.data.code == 503) {
        this.$message.error(res.data.message)
      }
    },

    // 更新学生信息
    updateStudent(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          var { data } = await this.$axios.post('/student/updateStudent', this.editStudent)
          if (data.code == 406) {
            // 更新失败
            this.$message({
              message: data.message,
              type: 'warning',
            })
          }
          if (data.code == 200) {
            // 更新成功
            this.$message({
              message: data.message,
              type: 'success',
            })
            this.dialogFormVisible = false
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

    //获取学生信息
    getStudentPage() {
      this.$axios
        .get('/student/getStudentPage', {
          params: {
            current: this.current,
            size: 8,
          },
        })
        .then((res) => {
          //   服务获取失败
          console.log(res.data.code)
          if (res.data.code == 503) {
            this.$message.error(res.data.message)
          }
          var totalSize = res.data[0].data
          var studentData = res.data[1].data
          this.totalSize = totalSize
          this.StudentTable = studentData

          this.StudentTable.forEach((element) => {
            if (element.sex == 1) {
              element.sex = '女'
            } else {
              element.sex = '男'
            }
          })
        })
    },
  },
}
</script>

<style scoped>
</style>