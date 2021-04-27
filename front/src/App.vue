<template>
  <div>
    <router-link to="/movies">예매하러가기</router-link>
    <router-link to="/search">예매 내역 확인</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>