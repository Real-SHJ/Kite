<template>
  <v-container class="">
    <h1 v-if="article">{{article.title}}</h1>
    <br>
    <br>
    <br>
    <div v-if="article" v-html="article.content"></div>
    <div class="btngrp">
      <ScrapDialog :article="article"/>
      <ShareDialog :article="article" :myFriends="myFriends"/>
    </div>
  </v-container>
</template>
<script>
import http from '../http-common'
import ScrapDialog from '../components/ScrapDialog.vue'
import ShareDialog from '../components/ShareDialog.vue'
export default {
  props: {
    id: String
  },
  components: {
    ScrapDialog,
    ShareDialog
  },
  data () {
    return {
      myFriends: [],
      article: null
    }
  },
  methods: {
    getarticle () {
      console.log(this.id)
      http.get(`/article/onearticle/${this.id}`)
        .then(res => {
          console.log(res)
          console.log(res.data)
          this.article = res.data.result
          console.log(this.article)
        })
        .catch(err => console.log(err))
    },
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
  },
  mounted () {
    this.getarticle()
    this.getMyFriends()
    console.log(this.id)
  },
  updated () {
  }
}

</script>

<style>
  .scrap {
    position: fixed;
    bottom: 20px;
    right: 100px;
  }
  .share {
    position: fixed;
    right: 20px;
    bottom: 20px;
  }
  .btngrp{
    position: absolute;
    bottom: 0;
    right: 0;
    z-index: inherit;
  }
</style>
