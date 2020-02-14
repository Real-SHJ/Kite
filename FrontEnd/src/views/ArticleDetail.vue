<template>
  <v-container class="">
    <h1 v-if="article">{{article.title}}</h1>
    <br>
    <br>
    <br>
    <div v-if="article" v-html="article.content"></div>
    <v-dialog v-model="dialog" max-width="290">
      <template v-slot:activator="{ on }">
        <v-btn
          class="scrap"
          dark
          fab
          color="pink"
          v-on="on"
        >
          <v-icon>fas fa-cut</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title></v-card-title>
        <v-card-text class="">이 기사를 스크랩 하시겠습니까?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
          <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialog" max-width="290">
      <template v-slot:activator="{ on }">
        <v-btn
          class="share"
          dark
          fab
          color="pink"
          v-on="on"
        >
          <v-icon>fas fa-share-alt</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title></v-card-title>
        <v-card-text class="">이 기사를 스크랩 하시겠습니까?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
          <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import http from '../http-common'
export default {
  props: {
    id: String
  },
  data () {
    return {
      dialog: false,
      article: null
    }
  },
  methods: {
    getarticle () {
      console.log(this.id)
      http.get(`/article/onearticle/${this.id}`)
        .then(res => {
          console.log(res)
          console.log(res.data.result)
          this.article = res.data.result
          console.log(this.article)
        })
        .catch(err => console.log(err))
    }
  },
  mounted () {
    this.getarticle()
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
</style>
