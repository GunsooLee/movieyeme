import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'
import MovieListView from '../views/MovieListView.vue'
import SeatsView from '../views/SeatsView.vue'
import YemeView from '../views/YemeView.vue'
import SearchView from '../views/SearchView.vue'

Vue.use(VueRouter)
  const routes = [
    {
      path: '/',
      component: Home
    },
    {
      path: '/movies',
      name: 'MovieListView',
      component: MovieListView,
      meta: { scrollToTop: true }
    },
    {
      path: '/seats',
      name: 'SeatsView',
      component: SeatsView,
      props: true,
      meta: { scrollToTop: true }
    },
    {
      path: '/yeme',
      name: 'YemeView',
      component: YemeView,
      props: true,
      meta: { scrollToTop: true }
    },
    {
      path: '/search',
      name: 'SearchView',
      component: SearchView,
      meta: { scrollToTop: true }
    },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
