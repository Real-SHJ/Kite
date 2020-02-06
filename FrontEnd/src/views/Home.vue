<template>
  <v-content>
    <h1>여기는 메인 페이지입니다...</h1>
    <ArticleList :articles="articles" />
    <InfiniteLoading @infinite="infiniteHandler"/>
  </v-content>
</template>

<script>
import http from '../http-common'
import ArticleList from '../components/ArticleList.vue'
import InfiniteLoading from 'vue-infinite-loading'
export default {
  name: 'home',
  data () {
    return {
      limit: 0,
      auth: [],
      articles: [],
      page: 0
    }
  },
  components: {
    ArticleList,
    InfiniteLoading
  },
  mounted () {
    this.init()
  },
  methods: {
    infiniteHandler ($state) {
      http
        .get('/article/info', {
          headers: {
            page: 1
          }
        })
        .then(({ data }) => {
          console.log(data)
          if (data.length) {
            this.page += 1
            this.articles.push(...data)
            $state.loaded()
          } else {
            $state.complete()
          }
        })
    }
  }
}
</script>
