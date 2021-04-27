<template>
  <div id="app">
    <h2>좌석 목록</h2>
    <div>
       영화 제목 : {{ this.show.mname }},
        상영관 : {{ this.show.tname }}, 
        상영 시간 : {{ this.show.time }} 
       <div class="wrap">
        <form>
          관람 인원 : 
          <select name="num"
          v-on:input="updateValue($event.target.value)">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
          </select>
        </form>
        <ul class="movies">
          <div class="chessboard">
            <div v-for="(line, index) in this.seats" v-bind:key="index">
                {{ index }}
              <div v-for="(cell, index2) in line" v-bind:key="index2" class="seat">
                <div v-if="cell !=null" class="reserved">
                  {{index2}}
                </div>
                <div v-else class="seatitem" v-on:click="select($event, index, index2)">
                  {{index2}}
                </div>

                
              </div>
            </div>

            <!-- <div @click="test">확인</div> -->
          </div>
        </ul>
      </div>
    </div>
    <div>
      <button class="next" @click="nextstep"> 다 음 </button>
    </div>
  </div>
</template>

<script>
// import { mapState, mapActions, mapMutations } from 'vuex'

export default {
  name: 'SeatsView',
  data: function () {
    return {
      show : [],
      seats : [],
      num : 1,
      selectedSeat : new Set(),
    }
  },
  computed: {
   
  },
  methods: {
    select: function(event, line, cell) {
      // this.targetId = event.currentTarget.id;
      if (event.target.id == "") {
        console.log(this.selectedSeat)
        if(this.selectedSeat.size < this.num){
          event.target.id = "select";
          this.selectedSeat.add(line * 100 + cell)
        } else {
          alert('관람 인원 초과')
        }
        
      } else {
          this.selectedSeat.delete(line * 100 + cell)
        event.target.id = "";
      }
    },
    updateValue: function(value){
      this.num = value;
    },
    nextstep(){
      if(this.selectedSeat.size != this.num){
        alert('관람 인원에 맞게 좌석을 선택해주세요')
      } else {
        this.$router.push({ name: 'YemeView', params: {show: this.show, selectedSeat: this.selectedSeat}})
      }
    },
  },
  created() {
      const filter = {
        params:{
          showid : this.$route.query.showid
        }
      }

      this.$axios.get("http://localhost:8443/yeme/show",filter)
      .then((res) => {
        console.log(res.data)
        this.show = res.data

        this.$axios.get("http://localhost:8443/yeme/theater",{
        params:{
          tid : this.show.theater_id
        }})
        .then((res2) => {
          
          for(var i = 0; i <= res2.data.height; i++){
            this.seats.push(new Array(res2.data.width))
          }

          this.$axios.get("http://localhost:8443/yeme/tickets",filter)
          .then((res3) => {
            let list = [...this.seats];
            res3.data.forEach(ts => {
              list[ts.width][ts.height] = ts.phone;
            })
            this.seats = list;
          })
        })
      })
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

.item {
  display: inline-block;
  margin: 15px;
}
.seat {
  display: inline-block;
}
.seatitem {
  border-color: black;
  color: gray;
  padding: 5px;
  margin: 10px;
  border-style: solid;
}
.reserved{
    border-color: black;
  color: gray;
  padding: 5px;
  margin: 10px;
  border-style: solid;
   background: red;
}
#select.seatitem {
  background: #fdcf01;
}
.message {
  padding-bottom: 20px;
  font-weight: bold;
}

</style>