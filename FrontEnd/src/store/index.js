import Vue from 'vue'
import Vuex from 'vuex'
import auth from './modules/auth'

Vue.use(Vuex)

export default new Vuex.Store({
  // vue에서 data의 역할
  state: {},
  // vue에서 methods 역할
  mutations: {},
  // vue에서 methods들을 묶어놓는다.?
  actions: {},
  //  위 세가지를 묶어서 하나의 모듈로 정의해 가져오는것도 가능하다.
  modules: {
    auth
  }
})
