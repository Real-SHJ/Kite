import Vue from 'vue'
import Vuex from 'vuex'
import Open from './modules/Open'
import auth from './modules/auth'
import memInfo from './modules/memInfo'
import color from './modules/color'
Vue.use(Vuex)

export default new Vuex.Store({
  // vue에서 컴포넌트 간의 공유하는 data의 역할
  state: {},
  // vue에서 methods 역할
  mutations: {},
  // vue에서 methods들을 묶어놓는다.?
  actions: {}, // 사용자의 입력에 따라 데이터를 변경
  //  위 세가지를 묶어서 하나의 모듈로 정의해 가져오는것도 가능하다.
  modules: {
    auth,
    Open,
    memInfo,
    color
  }
})
