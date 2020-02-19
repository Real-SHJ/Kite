const isToken = () => {
  const storage = sessionStorage.getItem('vue-session-key')
  if (storage) {
    const isLogin = JSON.parse(storage).hasOwnProperty('my-info')
    if (isLogin) {
      return true
    }
  }
  return false
}

const state = {
  token: isToken()
    ? JSON.parse(sessionStorage.getItem('vue-session-key'))['my-token']
    : null
}

const mutations = {
  setToken (state, token) {
    state.token = token
  }
}

const actions = {
  login (options, token) {
    options.commit('setToken', token)
  },
  logout (options) {
    options.commit('setToken', null)
  }
}

const getters = {
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
