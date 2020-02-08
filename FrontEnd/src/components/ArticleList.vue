<template>
  <v-container class="px-2">
    <v-card
      v-for="article in articles" :key="article.id"
      class="article-cards mx-auto my-10"
      max-width="700"
      style="height: 600px;"
    >
      <div @click="goDetail(article)">
        <v-img
          class="white--text align-end"
          height="400px"
          src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        >
          <v-avatar
            class="companyLogo"
            color="grey"
            size="100"
            tile
          >
            <v-img src="https://cdn.vuetifyjs.com/images/profiles/marcus.jpg"></v-img>
          </v-avatar>
        </v-img>
        <v-card-title>{{article.title}}</v-card-title>

        <v-card-subtitle class="pb-0 mb-5">Number 10</v-card-subtitle>

        <v-card-text class="text--primary">
          <div>Whitehaven Beach</div>

          <div>Whitsunday Island, Whitsunday Islands</div>
        </v-card-text>
      </div>
      <v-card-actions class="d-flex justify-end">
        <ScrapDialog :article="article"/>
        <ShareDialog :article="article"/>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
// import http from '../http-common'
import ScrapDialog from '../components/ScrapDialog.vue'
import ShareDialog from '../components/ShareDialog.vue'
export default {
  props: {
    articles: Array
  },
  components: {
    ScrapDialog,
    ShareDialog
  },
  data () {
    return {
      test: [1, 2, 3]
    }
  },
  methods: {
    goDetail (article) {
      this.$router.push({ path: `/articleDetail/${article.articleid}` })
    }
    // getArticle () {
    //   http.get('/article/list/')
    //     .then(res => {
    //     // 토큰 저장
    //       // console.log(res)
    //       this.articles = res.data.resvalue
    //       // console.log(this.articles)
    //     })
    //     .catch(err => console.log(err))
    // }
  },
  mounted () {
    // this.getArticle()
  }
}

</script>

<style>
  .article-cards {
    position: relative;
  }
  .companyLogo {
    position: absolute;
    top: 0;
    left: 0;
  }
</style>
