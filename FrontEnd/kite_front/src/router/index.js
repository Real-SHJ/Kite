<<<<<<< HEAD
import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/LoginPage.vue'
import SignUp from '../views/SignUp.vue'
import PersonalDataModify from '../views/PersonalDataModify.vue'
import Home from '../views/Home.vue'
=======
import Vue from 'vue';
import VueRouter from 'vue-router';
import LoginPage from '../views/LoginPage.vue';
import Home from '../views/Home.vue';
>>>>>>> a5e070104e235c060f0d792e4e28fb5758f344bc

Vue.use(VueRouter);

const routes = [

  {
    path: '/',
    name: 'home',
    component: Home,
  },

<<<<<<< HEAD
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
=======
  {
    path: '/loginPage',
    name: 'loginPage',
    component: LoginPage,
  },
];
>>>>>>> a5e070104e235c060f0d792e4e28fb5758f344bc

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
