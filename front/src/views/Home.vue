<template>
  <div class="home">
    <h1>~영화 예매 사이트 홈~</h1>
	<button @click="test">예매하러가기</button>
  </div>
</template>

<script>

export default {
  data() {
    return {
      msg: "Welcome to Your Vue.js App"
    };
  },
  methods: {
    test() {
      this.$router.push('movies')

    },
  },
};
</script>
