const state = {
  email: null,
  name: null,
  userid: null
}

const mutations = {
  setInfo (state, info) {
    if (info) {
      state.email = info.userEmail
      state.name = info.userName
      state.userid = info.userid
    } else {
      state.email = null
      state.name = null
      state.userid = null
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
      userName: state.name,
      userid: state.userid
    }
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
