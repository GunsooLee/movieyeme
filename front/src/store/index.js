import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
axios.defaults.baseURL = 'https://localhost:8443/MovieYeme/';

export default new Vuex.Store({
  state: {
    temp: 1
  },
  mutations: {
  },
  actions: {
  },
  plugins: [
  ]
})
