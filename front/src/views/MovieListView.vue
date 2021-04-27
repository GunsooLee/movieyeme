<template>
  <div id="app">
    <h2>영화 목록</h2>
    <div>
      <div class="left">
        <h3>영화 목록</h3>
        <v-container fluid grid-list-md >
          <v-layout row wrap v-for="movie in movies" :key="movie.id" style="margin:auto%" >
            <input type="radio" v-bind:id="movie[1].movie_id" name="test" v-bind:value="movie[1].movie_id" v-on:click="movieselect($event)">
            <label v-bind:for="movie[1].movie_id">{{movie[1].mname}}</label>
          </v-layout>
        </v-container>
      </div>
      <div class="mid">
        <h3>상영관</h3>
         <v-container fluid grid-list-md >
          <v-layout row wrap v-for="theater in selectedTheaters" :key="theater.id" style="margin:auto%" >
            <input type="radio" v-bind:id="theater[1].theater_id+'t'" name="test2" v-bind:value="theater[1].theater_id" v-on:click="theaterselect($event)">
            <label v-bind:for="theater[1].theater_id+'t'">{{theater[1].tname}}</label>
          </v-layout>
        </v-container>
      </div>
      <div class="right">
        <h3>상영 시간</h3>
         <v-container fluid grid-list-md >
          <v-layout row wrap v-for="show in selectedShows" :key="show.id" style="margin:auto%" >
            <input type="radio" v-bind:id="show[1].id+'s'" name="test3" v-bind:value="show[1].id" v-on:click="showselect($event)">
            <label v-bind:for="show[1].id+'s'">{{show[1].time}}
              예약인원 : {{show[1].reserved}} / {{show[1].seats}}
            </label>
          </v-layout>
        </v-container>
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
  name: 'MovieListView',
  data: function () {
    return {
      shows: [],
      movies: new Map(),
      theaters: new Map(),
      selectedMid : -1,
      selectedTheater : -1,
      selectedShow : -1,
    }
  },
  computed: {
    selectedTheaters : function() {
      var s = new Map()
      if(this.selectedMid < 0){
        for(var i = 0; i < this.shows.length; i++){
          s.set(this.shows[i].theater_id, this.shows[i]);
        }
      } else {
        for(var j = 0; j < this.shows.length; j++){
          if(this.shows[j].movie_id == this.selectedMid)
            s.set(this.shows[j].theater_id, this.shows[j]);
        }
      }
      return s;
    },

    selectedShows : function() {
      var s = new Map()
      if(this.selectedTheater < 0){
        for(var i = 0; i < this.shows.length; i++){
          s.set(this.shows[i].id, this.shows[i]);
        }
      } else {
        for(var j = 0; j < this.shows.length; j++){
          if(this.shows[j].movie_id == this.selectedMid && this.shows[j].theater_id == this.selectedTheater)
            s.set(this.shows[j].id, this.shows[j]);
        }
      }
      return s;
    }
  },
  methods: {
    select: function(event, mid) {
      // this.targetId = event.currentTarget.id;
      if (event.target.id == "") {
        this.selectedMid = mid;
        event.target.id = "select";
      } else {
        this.selectedMid = -1;
        event.target.id = "";
      }
    },

    movieselect(event) {
      this.selectedMid = event.target.value;
    },

    theaterselect(event) {
      this.selectedTheater = event.target.value;
    },

    showselect(event) {
      this.selectedShow = event.target.value;
    },

    nextstep(){
      if(this.selectedShow < 0){
        alert('영화 목록, 상영관, 상영시간을 모두 선택해주세요!')
      } else {
        for(var j = 0; j < this.shows.length; j++){
          if(this.shows[j].id == this.selectedShow){
            this.$router.push({ path: 'seats', query: { showid: this.shows[j].id} })
          } 
        }
      }
    }
  },
  created() {
    this.$axios.get("http://localhost:8443/yeme/shows").then(res => {
        this.shows = res.data;
        console.log(this.shows)
        for(var i = 0; i < this.shows.length; i++){
          this.movies.set(this.shows[i].movie_id, this.shows[i]);
          this.theaters.set(this.shows[i].theater_id, this.shows[i]);
          this.selectedTheaters.set(this.shows[i].theater_id, this.shows[i]);
        }
      });
  },
}
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  max-width: 720px;
}
div.left {
  width: 33%;
  float: left;
  box-sizing: border-box;
  border: solid 1px;
}
div.mid {
  width: 33%;
  float: left;
  box-sizing: border-box;
  border: solid 1px;
}
div.right {
  width: 33%;
  float: left;
  box-sizing: border-box;
  border: solid 1px;
}

h6.title{
  padding: 4px;
  width: 100%;
  border: solid 1px;
}

.next{
  background: wheat;
  padding: 6px;
  border: solid 1px;
}

#select.title {
  background: #fdcf01;
}

input + label{
  margin: 0;
  width: 100%;
  padding: .75em 1.5em;
  box-sizing: border-box;
  position: relative;
  display: inline-block;
  border: solid 1px #DDD;
  background-color: #FFF;
  line-height: 140%;
  text-align: center;
  box-shadow: 0 0 0 rgba(255, 255, 255, 0);
  transition: border-color .15s ease-out,  color .25s ease-out,  background-color .15s ease-out, box-shadow .15s ease-out;
  cursor: pointer;
}
input:checked + label{
	background-color: #4B9DEA;
  color: #FFF;
  box-shadow: 0 0 10px rgba(102, 179, 251, 0.5);
  border-color: #4B9DEA;
  z-index: 1;
}


</style>