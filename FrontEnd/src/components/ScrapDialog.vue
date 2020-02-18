<template>
  <v-dialog v-model="dialog" max-width="290">
    <template v-slot:activator="{ on }">
    <v-btn rounded class="mx-4" color="primary" v-on="on">
        <v-icon size="20px">fas fa-cut</v-icon>
    </v-btn>
    </template>
    <v-card>
    <v-card-title>{{article.articleid}}</v-card-title>
    <v-card-text class="">이 기사를 스크랩 하시겠습니까?</v-card-text>
    <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
        <v-btn color="green darken-1" text @click="scrapArticle">Agree</v-btn>
    </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import http from '../http-common'
export default {
  props: {
    article: Object
  },
  data () {
    return {
      userid: null,
      dialog: false,
      companylist: ''
    }
  },
  methods: {
    // islogin () {
    //   if (!this.$session.has('my-info')) {
    //     alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
    //     this.$router.push('/signup')
    //   }
    // },
    scrapArticle () {
      this.dialog = false
      console.log(this.userid)
      console.log(this.article.articleid)
      const fdata = new FormData()
      fdata.append('memberid', this.userid)
      fdata.append('articleid', this.article.articleid)
      http
        .post('/member/insertscrap', fdata)
        .then(res => {
          console.log(res.data.message)
          alert(res.data.message)
        })
        .catch(err => console.log(err))
    },
    useridCheck () {
      if (this.$session.has('my-info')) {
        this.userid = this.$session.get('my-info').userid
      }
    }
  },
  mounted () {
    this.useridCheck()
  }
}
</script>

<style>

</style>
