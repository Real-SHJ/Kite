const state = {
  email: null,
  name: null,
  userid: null,
  userImage: null,
  companylist: null
}

const mutations = {
  setInfo (state, info) {
    if (info) {
      state.email = info.userEmail
      state.name = info.userName
      state.userid = info.userid
      state.userImage = info.userImage
      state.companylist = info.companylist
    } else {
      state.email = null
      state.name = null
      state.userid = null
      state.userImage = null
      state.companylist = null
    }
  },
  updatecompany (state, info) {
    state.companylist = info.companylist
  }
}

const actions = {
  infoSave (options, info) {
    options.commit('setInfo', info)
  },
  infoDel (options) {
    options.commit('setInfo', null)
  },
  companyupdate (options, info) {
    options.commit('updatecompany', info)
  }
}

const getters = {
  userInfo (state) {
    return {
      userEmail: state.email,
      userName: state.name,
      userid: state.userid,
      userImage: state.userImage,
      companylist: state.companylist
    }
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
