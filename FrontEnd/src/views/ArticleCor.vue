<template>
  <v-content>
    <MainPageHeader />
    <!-- <MyArticleCor :com="company"/> -->
    <ArticleList :articles="articles" />
    <InfiniteLoading
      :identifier="company"
      @infinite="infiniteHandler"
    />
  </v-content>
</template>

<script>
import http from '../http-common'
// import MyArticleCor from '../components/MyArticleCor.vue'
import InfiniteLoading from 'vue-infinite-loading'
import ArticleList from '../components/ArticleList.vue'
import MainPageHeader from '../components/MainPageHeader.vue'
export default {
  name: 'Home',
  components: {
    // MyArticleCor
    MainPageHeader,
    InfiniteLoading,
    ArticleList
  },
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
  watch: {
    company: {
      handler () {
        this.articles = []
        this.page = 1
      }
    }
  },
  mounted () {
  },
  methods: {
    // initializeMovies () {
    //   console.log(`${this.company}입니다.`)
    //   const fdata = new FormData()
    //   const email = this.$session.get('my-info').userEmail
    //   fdata.append('email', email)
    //   fdata.append('company', this.company)
    //   http
    //     .post(`/article/infiloading/${this.page}`, fdata)
    //     .then(({ data }) => {
    //       this.articles = data.result
    //       console.log(data.result)
    //       console.log(this.page)
    //     })
    //   this.page += 1
    // },
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
  }
}
</script>
