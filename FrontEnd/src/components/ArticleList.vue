<template>
  <v-container>
    <v-row>
      <v-col
        v-for="(article, i) in articles"
        :key="i"
        class="images"
        outline
        tile
        :class="{'d-flex justify-end' : (i % 2 === 0)}"
        cols="6"
      >
        <div>
          <div>
            <v-card
              id="mycard"
              class="my-2"
              max-width="600"
              :img="`${article.image}`"
              height="350px"
              width="500px"
              tile
              @click="goDetail(article.articleid)"
            >
              <v-avatar
                class="companyLogo"
                size="100px"
              >
                <v-img
                  :src="`${article.logo}`"
                  height="60px"
                  width="60px"
                />
              </v-avatar>

              <div
                class="overay textwrap"
                style="color:white;"
              >
                {{ article.summary }}
              </div>
            </v-card>
            <p
              v-if="article.title.length > 31"
              class="article-title"
            >
              {{ article.title.slice(0, 31) }}...
            </p>
            <p
              v-else
              class="article-title"
            >
              {{ article.title }}
            </p>
          </div>
          <!-- <div class="btngrp">
            <ScrapDialog :article="article"/>
            <ShareDialog :article="article" :myFriends="myFriends"/>
          </div> -->
        </div>
      </v-col>
      <v-responsive
        v-if="n === 2"
        :key="`width-${i}`"
      />
    </v-row>
    <v-snackbar
      v-model="snackbar"
      color="red"
      :timeout="timeout"
      :bottom="true"
      :right="true"
    >
      {{ text }}
      <v-btn
        color="white"
        text
        @click="snackbar = false"
      >
        FAIL
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import http from '../http-common'
// import ScrapDialog from '../components/ScrapDialog.vue'
// import ShareDialog from '../components/ShareDialog.vue'
export default {
  components: {
    // ScrapDialog,
    // ShareDialog
  },
  props: {
    articles: Array
  },
  data () {
    return {
      snackbar: false,
      // myId: null,
      test: [1, 2, 3],
      myFriends: [],
      n: 0,
      AuthenticatedCheck: null
    }
  },
  mounted () {
    // this.getArticle()
    // this.userIdCheck()
    this.getMyFriends()
  },
  updated () {
    // this.AuthenticatedCheck = this.$session.has('my-token')
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
            })
        }
      }, 1000)
    },
    goDetail (articleid) {
      if (this.AuthenticatedCheck) {
        this.snackbar = true
        this.$router.push('/signup')
      } else {
        this.$router.push({ path: `/articleDetail/${articleid}` })
      }
    }
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
    height: 50%;
  }
  .article-title{
    /* color:white; */
    font-size: 18px;
    font-weight: bold;
    /* margin:auto; */
    /* max-height:50px; */
    /* top: 0; */
    /* margin-left: 2%; */
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
