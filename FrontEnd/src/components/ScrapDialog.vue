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
      useremail: this.$session.get('my-info').userEmail,
      dialog: false
    }
  },
  methods: {
    scrapArticle () {
      this.dialog = false
      console.log(this.useremail)
      console.log(this.article.articleid)
      const fdata = new FormData()
      fdata.append('email', this.useremail)
      fdata.append('articleid', this.article.articleid)
      http
        .post('/member/scrap', fdata)
        .then(res => {
          console.log(res)
        })
        .catch(err => console.log(err))
    }
  }
}
</script>

<style>

</style>
