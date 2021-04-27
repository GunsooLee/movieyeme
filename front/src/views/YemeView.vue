<template>
  <div id="app">
    <h2>예매 하기</h2>
    <div>
      영화 이름 : {{this.show.mname}}
    </div>
    <div>
      상영관 : {{this.show.tname}}
    </div>
    <div>
      상영 시간 : {{this.show.time}}
    </div>
    <div>
      예매 좌석 : 
        <li v-for="seat in selectedSeat" :key="seat">
          {{parseInt(seat/100)}}행 {{parseInt(seat%100)}}열
        </li>
        
    </div>
    <form v-on:submit="onSubmitForm"> 
        <!-- v-model input에서 값을 받아올 이름 지정 --> 
        <div>휴대폰 번호 : <input type="tel" v-model="phone">
        </div>
      <button class="next"> 예매 하기 </button>
      </form>
    <div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'YemeView',
  data: function () {
    return {
      show: [],
      selectedSeat: new Set(),
      phone: '0100000000',
    }
  },
  computed: {
   
  },
  methods: {
   onSubmitForm(e){
        e.preventDefault();
        var ss = []
        this.selectedSeat.forEach(element => {
          ss.push(element)
        });


      this.$axios.post("http://localhost:8443/yeme/yeme",null, {
                params: {
                    showid : this.show.id,
                    phone : this.phone,
                    seats: encodeURI(JSON.stringify(ss))
                }
            }
        )
        .then(res => {
            alert('예매가 완료되었습니다!');
            console.log(res.data)
            
            this.$router.push('/')
        });

    },
  },
  created() {
    this.show = this.$route.params.show
    this.selectedSeat = this.$route.params.selectedSeat
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
.message {
  padding-bottom: 20px;
  font-weight: bold;
}
</style>