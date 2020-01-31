<template>
  <v-content>
    <h1>여기는 메인 페이지입니다...</h1>
    <ArticleList/>
    <InfiniteLoading></InfiniteLoading>
  </v-content>
</template>

<script>
import ArticleList from '../components/ArticleList.vue'
import InfiniteLoading from 'vue-infinite-loading'
import http from '../http-common'
export default {
  name: 'home',
  data () {
    return {
      auth: []
    }
  },
  components: {
    ArticleList,
    InfiniteLoading
  },
  mounted () {
    this.init()
    this.send()
  },
  methods: {
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
        .get('/test/callback/' + this.auth[0] + '/' + this.auth[1])
        .then(res => {
          console.log(res.data)
        })
    }
  }
}
</script>

<style>

</style>
