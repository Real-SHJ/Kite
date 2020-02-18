const state = {
  colorReset: false
}

const mutations = {
  resetColor (state, reset) {
    state.colorReset = !state.colorReset
  }
}

const actions = {
  colorChanger (options, reset) {
    options.commit('resetColor', reset)
  }
}

const getters = {
  colorControl (state) {
    return state.colorReset
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
