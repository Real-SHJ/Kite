<template>
  <v-dialog
    v-model="dialog"
    max-width="290"
  >
    <template v-slot:activator="{ on }">
      <v-btn
        class="scrap"
        dark
        color="pink"
        v-on="on"
      >
        <v-icon>fas fa-cut</v-icon>
      </v-btn>
    </template>
    <v-card>
      <br>
      <br>
      <v-card-text
        class="text-center"
        style="font-size: 130%"
      >
        이 기사를 스크랩 하시겠습니까?
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
          @click="scrapArticle"
        >
          Agree
        </v-btn>
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
  mounted () {
    this.useridCheck()
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
      const fdata = new FormData()
      fdata.append('memberid', this.userid)
      fdata.append('articleid', this.article.articleid)
      http
        .post('/member/insertscrap', fdata)
        .then(res => {
          alert(res.data.message)
        })
        .catch(err => console.log(err))
    },
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
