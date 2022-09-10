<template>
  <div class="head-bar">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="logout">
      <div class="systemName">学生信息管理系统</div>

      <el-submenu index="1" class="user">
        <template slot="title">{{ userName }}</template>
        <el-menu-item index="1-1">退出登录</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'VueCliSecondHeadbar',
  data() {
    return {
      userName: '',
      activeIndex: '1',
      activeIndex2: '1',
    }
  },
  mounted() {
    this.userName = this.$route.query.managerName
  },
  methods: {
    logout(key, keyPath) {
      this.$store.commit('set_token', null)
      this.$router.push('/')
    },
  },
}
</script>

<style scoped>
.systemName {
  float: left;
  width: 250px;
  line-height: 70px;
  color: #409eff;
  margin-left: 8px;
}
.head-bar {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 18px;
}
.head-bar .user {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  float: right;
}
</style>