import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import ArticleDetail from '../views/ArticleDetail.vue'
import SignUp from '../views/SignUp.vue'
import PersonalDataModify from '../views/PersonalDataModify.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/loginpage',
    name: 'loginpage',
    component: LoginPage
  },

  {
    path: '/articledetail',
    name: 'articledetail',
    component: ArticleDetail
  },

  {
    path: '/signup',
    name: 'signup',
    component: SignUp
  },

  {
    path: '/personaldatamodify',
    name: 'personaldatamodify',
    component: PersonalDataModify
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
