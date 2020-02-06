<template>
  <v-dialog v-model="dialog" max-width="290">
    <template v-slot:activator="{ on }">
    <v-btn class="mx-4" color="primary" icon dark v-on="on">
        <v-icon size="24px">fas fa-cut</v-icon>
    </v-btn>
    </template>
    <v-card>
    <v-card-title>{{article.articleid}}</v-card-title>
    <v-card-text class="">이 기사를 스크랩 하시겠습니까?</v-card-text>
    <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="scrapArticle">Disagree</v-btn>
        <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
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
      dialog: false
    }
  },
  methods: {
    scrapArticle () {
      this.dialog = false
      const fdata = new FormData()
      fdata.article('memberid', 1)
      fdata.article('articleid', this.article.articleid)
      http
        .post('/member/scraparticle', fdata)
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
