<template>
  <v-dialog
    v-model="dialog"
    max-width="500"
    max-height="500"
  >
    <template v-slot:activator="{ on }">
      <v-btn
        rounded
        class="mx-4"
        color="primary"
        v-on="on"
      >
        <v-icon size="20px">
          fas fa-cut
        </v-icon>
      </v-btn>
    </template>
    <v-card>
      <!-- <v-card-title>{{article.articleid}}</v-card-title> -->
      <v-card-text class="">
        기사를 스크랩하기 위해 페이지를 이동 하시겠습니까?
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn
          color="green darken-1"
          text
          @click="dialog = false"
        >
          Disagree
        </v-btn>
        <v-btn
          color="green darken-1"
          text
          @click="goHome"
        >
          Agree
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
// import http from '../http-common'
export default {
  props: {
    // article: Object
  },
  data () {
    return {
      userid: null,
      dialog: false
    }
  },
  mounted () {
    this.useridCheck()
  },
  methods: {
    goHome () {
      this.$router.push('/')
    },
    // scrapArticle () {
    //   this.dialog = false
    //   console.log(this.userid)
    //   console.log(this.article.articleid)
    //   const fdata = new FormData()
    //   fdata.append('memberid', this.userid)
    //   fdata.append('articleid', this.article.articleid)
    //   http
    //     .post('/member/insertscrap', fdata)
    //     .then(res => {
    //       console.log(res.data.message)
    //     })
    //     .catch(err => console.log(err))
    // },
    useridCheck () {
      if (this.$session.has('my-info')) {
        this.userid = this.$session.get('my-info').userid
      }
    }
  }
}
</script>

<style>

</style>
