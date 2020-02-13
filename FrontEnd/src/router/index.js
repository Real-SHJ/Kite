import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Analysis from '../views/Analysis.vue'
import ArticleCorpo from '../views/ArticleCor.vue'
import ArticleDetail from '../views/ArticleDetail.vue'
import Empty from '../views/Empty.vue'
import Friend from '../views/Friend.vue'
import ForceDirected from '../components/ForceDirected.vue'
import NaverLogin from '../components/NaverLogin.vue'
import IndiRepository from '../views/IndiRepository.vue'
import SelectCompany from '../views/SelectCompany.vue'
import SignUp from '../views/SignUp.vue'
import WordCloud from '../components/WordCloud.vue'
import Test from '../views/Test.vue'
import IndiRepo from '../views/IndiRepo.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },

  {
    path: '/indirepo',
    name: 'indirepo',
    component: IndiRepo
  },

  {
    path: '/analysis',
    name: 'analysis',
    component: Analysis
  },
  {
    path: '/articlecorpo/:company',
    name: 'articlecorpo',
    component: ArticleCorpo,
    props: true
  },
  {
    path: '/articleDetail/:id',
    name: 'articledetail',
    component: ArticleDetail,
    props: true
  },
  {
    path: '/empty',
    name: 'empty',
    component: Empty
  },
  {
    path: '/friend',
    name: 'friend',
    component: Friend
  },
  {
    path: '/forcedirected',
    name: 'forcedirected',
    component: ForceDirected
  },
  {
    path: '/indirepository',
    name: 'indirepository',
    component: IndiRepository
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
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignUp
  },
  {
    path: '/wordcloud',
    name: 'wordcloud',
    component: WordCloud
  },
  {
    path: '/test',
    name: 'test',
    component: Test
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
