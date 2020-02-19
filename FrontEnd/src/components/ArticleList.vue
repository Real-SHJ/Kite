<template>
  <v-flex>
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
    <v-row>
      <div
      class="d-flex justify-center"
      outline
      tile
      v-for="(article,i) in articles" :key="article.id">
        <v-col cols="4"></v-col>
          <v-col class="images">
              <v-card
              @click='goDetail(article.articleid)'
              class="my-2"
                  max-width="600"
                  id = "mycard"
                  :img = "`${article.image}`"
                  height="350px"
                  width="500px"
                  tile
              >
                  <v-avatar
                    class="companyLogo"
                    size="100px"
                  >
                    <v-img :src="`${article.logo}`" height="60px" width="60px"></v-img>
                  </v-avatar>

                  <v-card-actions class="btngrp">
                    <ScrapDialog :article="article"/>
                    <ShareDialog :article="article" :myFriends="myFriends"/>
                  </v-card-actions>
              <div class="overay textwrap" style="color:white;">
                {{article.summary}}
              </div>
            <!-- <v-card-title>{{article.title}}</v-card-title>
            <v-divider class="mx-4"></v-divider>

            <v-card-subtitle class="pb-0 mb-5" v-html="article.newspaper"></v-card-subtitle>

            <v-card-text>
              <div v-html="article.summary"></div>
            </v-card-text>
            <v-divider class="mx-4"></v-divider> -->

          <!-- <div @click="goDetail(article)">
          </div> -->
          </v-card>
            <v-row
            class="row"
              >
              {{article.title}}
            </v-row>
        </v-col>
        <v-responsive
          v-if="n === 2"
          :key="`width-${i}`"
        ></v-responsive>
      </div>
    </v-row>

  </v-flex>
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
      // myId: null,
      test: [1, 2, 3],
      myFriends: [],
      n: 0
    }
  },
  methods: {
    init () {
      this.drawer = true
      let companylist = null
      companylist = this.userInfo.companylist
      this.info = companylist.split(',')
    },
    getMyFriends () {
      setTimeout(() => {
        if (this.$session.has('my-info')) {
          const myId = this.$session.get('my-info').userid
          http
            .get(`/member/friendlist/${myId}`)
            .then((res) => {
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
    // this.getArticle()
    // this.userIdCheck()
    this.getMyFriends()
  },
  updated () {
  }
}

</script>

<style scoped>
  .companyLogo {
    position: absolute;
    top: 0;
    right: 0;
    margin-right: 3%;
  }
  #mycard {
    background-color: rgba(255, 255, 255, 0.300);
    color: #000000 !important;
  }

  .textwrap{
    /* width: 100%; */
    display: inline-block;
    white-space: normal;
    overflow:hidden;
    text-overflow: ellipsis;
    word-wrap: break-word;
    line-height: 1.2;
    height: 3.6;
    text-align: left;
    display:-webkit-box;
    -webkit-line-clamp:3;
    -webkit-box-orient: vertical;
  }

  .overay {
    position: absolute;
    bottom: 0;
    left:0;
    right:0;
    background-color: rgba(0, 0, 0, 0.6);
    width: 100%;
    overflow: hidden;
    height: 0;
    transition: 0.3s ease;
  }

  .images:hover .overay{
    height: 45%;
  }
  .row{
    /* color:white; */
    font-size: 18px;
    font-weight: bold;
    /* margin:auto; */
    /* max-height:50px; */
    /* top: 0; */
    margin-left: 2%;
    /* right:0; */
    /* margin-bottom: 10%; */
    /* z-index: inherit; */
  }
  .btngrp{
    position: absolute;
    bottom: 0;
    right: 0;
    z-index: inherit;
  }
</style>
