// Opening page를 위한 js
// actions에 접근할 때는 dispatch, mutations에 접근할 때는 commit
// mutations: state 값을 변화시키는 로직
// actions: business 로직
const state = {
  open: false
}

const mutations = {
  setOpen (state) {
    if (state.open === false) { state.open = true } else { state.open = false }
  }
}

const getters = {
  getOpen (state) {
    return state.open
  }
}

export default {
  state,
  mutations,
  getters
}
