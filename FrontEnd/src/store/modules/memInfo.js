const state = {
  email: null
}

const mutations = {
  setInfo (state, email) {
    state.email = email
  }
}

const actions = {
  infoSave (options, email) {
    options.commit('setInfo', email)
  }
}

const getters = {
  AuthenticatedCheck (state) {
    return !!state.token
  },
  requestHeader (state) {
    return {
      headers: {
        Authorization: `JWT ${state.token}`
      }
    }
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
