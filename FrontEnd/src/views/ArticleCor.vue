<template>
  <v-content>
    <h1>여기는 {{company}} 페이지입니다...</h1>
    <!-- <MyArticleCor :com="company"/> -->
    <ArticleList :articles="articles" />
    <InfiniteLoading @infinite="infiniteHandler"></InfiniteLoading>
  </v-content>
</template>

<script>
import http from '../http-common'
// import MyArticleCor from '../components/MyArticleCor.vue'
import InfiniteLoading from 'vue-infinite-loading'
import ArticleList from '../components/ArticleList.vue'
export default {
  name: 'home',
  props: {
    company: String
  },
  data () {
    return {
      limit: 0,
      auth: [],
      articles: [],
      com: '',
      page: 1
    }
  },
  components: {
    // MyArticleCor
    InfiniteLoading,
    ArticleList
  },
  mounted () {
  },
  methods: {
    initializeMovies () {
      console.log(`${this.company}입니다.`)
      const fdata = new FormData()
      const email = this.$session.get('my-info').userEmail
      fdata.append('email', email)
      fdata.append('company', this.company)
      http
        .post(`/article/infiloading/${this.page}`, fdata)
        .then(({ data }) => {
          this.articles = data.result
          console.log(data.result)
          console.log(this.page)
        })
      this.page += 1
    },
    infiniteHandler ($state) {
      console.log(this.company)
      const fdata = new FormData()
      const email = this.$session.get('my-info').userEmail
      fdata.append('email', email)
      fdata.append('company', this.company)
      console.log(email)
      // const headers = {
      //   email: email,
      //   company: this.company
      // }
      console.log(`/article/infiloading/${this.page}`)
      http
        // .get(`/article/infiloading/${this.page}`, { headers })
        .post(`/article/infiloading/${this.page}`, fdata)
        .then(({ data }) => {
          console.log(data.result)
          console.log(this.page)
          if (data.result.length) {
            this.page += 1
            const tempArticle = data.result
            for (var article of tempArticle) {
              this.articles.push(article)
            }
            console.log(this.articles)
            $state.loaded()
          } else {
            $state.complete()
          }
        })
    }
  },
  computed: {
    loading () {
      return !this.articles
    }
  },
  watch: {
    company: {
      handler () {
        this.articles = []
        this.page = 1
        console.log(this.company)
        console.log(this.articles)
        console.log(this.page)
        this.initializeMovies()
      }
    }
  }
}
</script>
