const state = {
  email: null,
  name: null
}

const mutations = {
  setInfo (state, info) {
    if (info) {
      state.email = info.userEmail
      state.name = info.userName
    } else {
      state.email = null
      state.name = null
    }
  }
}

const actions = {
  infoSave (options, info) {
    options.commit('setInfo', info)
  },
  infoDel (options) {
    options.commit('setInfo', null)
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
