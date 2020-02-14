<template>
  <v-container class="px-2">
    <!-- <v-carousel hide-delimiters>
      <div
        v-for="(article,i) in articles"
        :key="i"
      >
        <v-carousel-item>
          <v-row>
            <v-col class="p-0">
              <img :src="article.url" class="p-0" style="height: 100%; width: 100%;">
            </v-col>
            <v-col class="p-0">
              <v-row class="p-0">
                <img :src="article.url" class="p-0" style="height: 250px; width: 454px;">
              </v-row>
              <v-row class="p-0">
                <img :src="article.url" class="p-0" style="height: 250px; width: 454px;">
              </v-row>
            </v-col>
          </v-row>
        </v-carousel-item>
        <v-carousel-item>
          <v-row style="height: 50%">
            <v-col class="p-0">
              <img :src="article.url" class="p-0" style="height: 100%; width: 100%;">
            </v-col>
          </v-row>
          <v-row class="p-0">
            <v-col class="p-0">
                <img :src="article.url" class="p-0" style="height: 250px; width: 454px;">
            </v-col>
            <v-col class="p-0">
              <img :src="article.url" class="p-0" style="height: 250px; width: 454px;">
            </v-col>
          </v-row>
        </v-carousel-item>
        <v-carousel-item>
          <v-row style="height: 100%;">
            <v-col cols="4" class="p-0">
              <img :src="article.url" class="p-0" style="height: 100%; width: 100%;">
            </v-col>
            <v-col cols="4" class="p-0">
              <img :src="article.url" class="p-0" style="height: 100%; width: 100%;">
            </v-col>
            <v-col cols="4" class="p-0">
              <img :src="article.url" class="p-0" style="height: 100%; width: 100%;">
            </v-col>
          </v-row>
        </v-carousel-item>
      </div>
    </v-carousel> -->
    <v-card
      v-for="article in articles" :key="article.id"
      class="article-cards mx-auto my-10"
      max-width="700"
      style="height: 600px;"
      id = "mycard"
    >
      <div @click="goDetail(article)">
        <v-img
          class="align-end"
          height="400px"
          :src="`${article.image}`"
        >
          <v-avatar
            class="companyLogo"
            size="100"
          >
            <v-img :src="`${article.logo}`" height="60px" width="60px"></v-img>
          </v-avatar>
        </v-img>
        <v-card-title>{{article.title}}</v-card-title>

        <v-card-subtitle class="pb-0 mb-5">Number 10</v-card-subtitle>

        <v-card-text>
          <div>Whitehaven Beach</div>

          <div>Whitsunday Island, Whitsunday Islands</div>
        </v-card-text>
      </div>
      <v-card-actions class="d-flex justify-end">
        <ScrapDialog :article="article"/>
        <ShareDialog :article="article" :myFriends="myFriends"/>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import http from '../http-common'
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
      myId: null,
      test: [1, 2, 3],
      myFriends: []
    }
  },
  methods: {
    userIdCheck () {
      if (this.$session.has('my-info')) {
        console.log('니니니닌')
        console.log(this.$session.get('my-info').userid)
        this.myId = this.$session.get('my-info').userid
        console.log(this.myId)
      }
    },
    getMyFriends () {
      setTimeout(() => {
        if (this.myId) {
          http
            .get(`/member/friendlist/${this.myId}`)
            .then((res) => {
              console.log(res)
              this.myFriends = res.data.flist
              console.log(this.myFriends)
            })
        }
      }, 1000)
    },
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
    console.log('!!!!!!!!!!!!!!!!!!!!!!!')
    console.log(this.myId)
    // this.getArticle()
    this.getMyFriends()
    this.userIdCheck()
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
  #mycard {
    background-color: #3A3B3C;
    color: #F1F1F1 !important;
  }
</style>
