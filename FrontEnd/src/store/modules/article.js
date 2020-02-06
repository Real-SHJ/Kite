const state = {
  articles: []
}

const mutations = {
  setArticle (state, articles) {
    state.articles = articles
  }
}

const actions = {
  articleSave (options, articles) {
    options.commit('setArticle', articles)
  }
}

const getters = {
  allArticles (state) {
    return state.articles
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
