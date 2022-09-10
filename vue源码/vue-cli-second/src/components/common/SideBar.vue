<template>
  <el-row class="tac">
    <el-col :span="12">
      <h5>导航栏</h5>
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-user-solid"></i>
            <span>学生信息</span>
          </template>
          <el-menu-item-group>
            <router-link to="/AddStudentForm">
              <el-menu-item index="1-1">导入学生信息</el-menu-item>
            </router-link>
            <router-link to="/StduentTable">
              <el-menu-item index="1-2">查看学生信息</el-menu-item>
            </router-link>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-user-solid"></i>
            <span>教学课程</span>
          </template>
          <el-menu-item-group>
            <router-link to="/AddLessonForm">
              <el-menu-item index="2-1">添加教学课程</el-menu-item>
            </router-link>
            <router-link to="/LessonTable">
              <el-menu-item index="2-2">查看课程信息</el-menu-item>
            </router-link>
          </el-menu-item-group>
        </el-submenu>
        <router-link to="/addManager">
          <el-menu-item index="3">
            <i class="el-icon-setting"></i>
            <span slot="title">管理员添加</span>
          </el-menu-item>
        </router-link>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'VueCliSecondSidebar',

  data() {
    return {}
  },

  mounted() {},

  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath)
    },
  },
}
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}

.sidebar-el-menu:not(.el-menu--collapse) {
  width: 200px;
}

.sidebar > ul {
  height: 100%;
}
</style>