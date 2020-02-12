<template>
  <v-content>
    <!-- <Menu/> -->
    <MainPageHeader/>
    <ArticleList :articles="articles" />
    <!-- <InfiniteLoading @infinite="infiniteHandler"/> -->
  </v-content>
</template>

<script>
import http from '../http-common'
import ArticleList from '../components/ArticleList.vue'
import MainPageHeader from '../components/MainPageHeader.vue'
// import Menu from '../components/Menu2'
// import InfiniteLoading from 'vue-infinite-loading'
export default {
  name: 'home',
  data () {
    return {
      AuthenticatedCheck: this.$session.has('my-token'),
      limit: 0,
      auth: [],
      myArticles: [],
      articles: [],
      page: 1
    }
  },
  components: {
    // Menu,
    MainPageHeader,
    ArticleList
    // InfiniteLoading
  },
  mounted () {
    this.AuthenticatedCheck = this.$session.has('my-token')
    this.allArticles()
  },
  updated () {
    this.AuthenticatedCheck = this.$session.has('my-token')
  },
  methods: {
    allArticles () {
      http
        .get('article/list')
        .then((res) => {
          console.log(res)
          this.articles = res.data.resvalue
        })
    }
    // infiniteHandler ($state) {
    //   setTimeout(() => {
    //     if (this.AuthenticatedCheck) {
    //       const fdata = new FormData()
    //       const email = this.$session.get('my-info').userEmail
    //       fdata.append('email', email)
    //       console.log(email)
    //       // const headers = {
    //       //   email: email
    //       // }
    //       console.log(`/article/infiloading/${this.page}`)
    //       http
    //         // .get(`/article/infiloading/${this.page}`, { headers })
    //         .post(`/article/infiloading/${this.page}`, fdata)
    //         .then(({ data }) => {
    //           console.log(data.result)
    //           if (data.result.length) {
    //             this.page += 1
    //             const tempArticle = data.result
    //             for (var article of tempArticle) {
    //               this.myArticles.push(article)
    //             }
    //             console.log(this.myArticles)
    //             this.articles = this.myArticles
    //             $state.loaded()
    //           } else {
    //             $state.complete()
    //           }
    //         })
    //     } else {
    //       http
    //         .get('/article/list')
    //         .then(res => {
    //           console.log(res.data.resvalue)
    //           this.articles = res.data.resvalue.slice(0, 5)
    //         })
    //         .catch(err => console.log(err))
    //     }
    //   }, 1000)
    // }
  }
}
</script>
