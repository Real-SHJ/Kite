import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import SignUp from '../views/SignUp.vue'
import PersonalDataModify from '../views/PersonalDataModify.vue'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'home',
    component: Home
  },

  {
    path: '/loginPage',
    name: 'loginPage',
    component: LoginPage
  },

  {
    path: '/signUp',
    name: 'signUp',
    component: SignUp
  },

  {
    path: '/personalDataModify',
    name: 'personalDataModify',
    component: PersonalDataModify
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
