const isToken = () => {
  const storage = sessionStorage.getItem('vue-session-key')
  if (storage) {
    const isToken = JSON.parse(storage).hasOwnProperty('my-session-token')
    if (isToken) {
      return true
    }
  }
  return false
}

const state = {
  token: isToken()
    ? JSON.parse(sessionStorage.getItem('vue-session-key'))['my-session-token']
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
