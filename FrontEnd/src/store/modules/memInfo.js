const state = {
  email: null,
  name: null
}

const mutations = {
  setInfo (state, info) {
    state.email = info.userEmail
    state.name = info.userName
  }
}

const actions = {
  infoSave (options, info) {
    options.commit('setInfo', info)
  }
}

const getters = {
  userInfo (state) {
    return {
      userEmail: state.email,
      userName: state.name
    }
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
