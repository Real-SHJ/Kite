<template>
  <v-container class="d-flex ">
    <h1>여기는 상세보기 페이지입니다.</h1>
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
    id: Number
  },
  data () {
    return {
      dialog: false,
      detail: 'yes'
    }
  },
  methods: {
    getarticle () {
      http.get(`/article/selectone/${this.id}`)
        .then(res => {
          console.log(res)
        })
        .catch(err => console.log(err))
    }
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
