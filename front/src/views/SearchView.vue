<template>
  <div id="app">
    <h2>전화번호로 검색하기</h2>
    <form v-on:submit="onSubmitForm"> 
        <div>휴대폰 번호 : <input type="tel" v-model="phone">
        </div>
      <button class="next"> 검색 하기 </button>
      </form>
    <div>
      <ul>
      <li v-for="t in tickets" :key="t">
        <div>
          영화 이름 : {{t.mname}}, 상영관 : {{t.tname}}, 좌석 : {{t.width}}행 {{t.height}}열
        </div>
      </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchView',
  data: function () {
    return {
      phone: '0100000000',
      tickets: [],
    }
  },
  computed: {
  },
  methods: {
   onSubmitForm(e){
        e.preventDefault();
      const filter = {
        params:{
          phone : this.phone,
        }
      }
      this.$axios.get("http://localhost:8443/yeme/yemes",filter)
      .then((res) => {
        this.tickets = res.data
      })
    },
  },
  created() {
   
  },
}
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  max-width: 1080px;
}

</style>