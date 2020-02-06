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
      this.http.get('/article/list' + (this.limit + 10)) // api에 url 삽입
        .then(response => {
          setTimeout(() => { // 스크롤 페이징을 띄우기 위한 시간 지연(1초)
            if (response.data.length) {
              this.articles = this.articles.concat(response.data.title)
              $state.loaded() // 데이터 로딩
              this.limit += 10
              if (this.articles.length / 10 === 0) {
                $state.complete() // 데이터가 없으면 로딩 끝
              }
            } else {
              $state.complete()
            }
          }, 1000)
        }).catch(error => {
          console.error(error)
        })
    },
    init () {
      var str1 = location.href.split('#')
      var str2 = str1[1].split('&')
      var accesstoken = str2[0].substring(13)
      var state = str2[1].substring(6)
      this.auth[0] = accesstoken
      this.auth[1] = state
      window.console.log(this.auth[0])
      window.console.log(this.auth[1])
      this.send()
    },
    send () {
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
