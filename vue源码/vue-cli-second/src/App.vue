<template>
  <div id="container">
    <router-view />
  </div>
</template>

<script>
import router from '@/router/index.js'
export default {
  data() {
    return {}
  },
  mounted() {},

  methods: {},
  components: {},
}
</script>

<style scoped>
#container {
  padding: 0px;
  margin: 0px;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 100%;
  border: hidden;
}
</style>