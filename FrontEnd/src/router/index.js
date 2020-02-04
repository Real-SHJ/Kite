import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import ArticleDetail from '../views/ArticleDetail.vue'
import SignUp from '../views/SignUp.vue'
import NaverLogin from '../components/NaverLogin.vue'
import IndiRepository from '../views/IndiRepository.vue'
import FriendsSearch from '../views/FriendsSearch.vue'
import FriendsManage from '../views/FriendsManage.vue'
import SelectCompany from '../views/SelectCompany.vue'

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
    path: '/indirepository',
    name: 'indirepository',
    component: IndiRepository
  },

  {
    path: '/friendssearch',
    name: 'friendssearch',
    component: FriendsSearch
  },

  {
    path: '/friendsmanage',
    name: 'friendsmanage',
    component: FriendsManage
  },

  {
    path: '/naver',
    name: 'naver',
    component: NaverLogin
  },
  {
    path: '/selectcompany',
    name: 'selectcompany',
    component: SelectCompany
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
