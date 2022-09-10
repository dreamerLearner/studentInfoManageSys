<template>
  <div>
    <div class="header">
      <HeadBar />
    </div>
    <div class="side">
      <SideBar />
    </div>
    <div class="content-box">
      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import HeadBar from '@/components/common/HeadBar.vue'
import SideBar from '@/components/common/SideBar.vue'
import StudentTable from '@/components/Student/StudentTable.vue'
import store from '../store/store.js'

export default {
  name: 'VueCliSecondMain',

  data() {
    return {}
  },

  mounted() {
    this.user = this.$route.params.user
  },

  methods: {},
  components: {
    HeadBar,
    SideBar,
    StudentTable,
  },
}
</script>

<style scoped>
.content-box {
  position: absolute;
  left: 200px;
  right: 0;
  top: 70px;
  bottom: 0;
  background: #f0f0f0;
}

.content {
  width: auto;
  height: 100%;
  padding: 10px;
  overflow-y: scroll;
  box-sizing: border-box;
}
</style>