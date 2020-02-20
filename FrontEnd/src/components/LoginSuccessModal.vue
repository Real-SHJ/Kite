<template>
  <v-dialog
    v-model="dialog"
    width="unset"
  >
    <template v-slot:activator="{ on }">
      <v-btn
        rounded
        class="mx-4"
        color="primary"
        v-on="on"
      >
        <!-- <v-btn @click="login" size="20px" rounded color="tea1 accent-3" dark>SIGN IN </v-btn> -->
        <!-- <v-icon size="20px">fas fa-cut</v-icon> -->
      </v-btn>
    </template>
    <v-card>
      <!-- <v-card-title>{{article.articleid}}</v-card-title> -->
      <v-card-text class="">
        로그인 성공입니다!
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <!-- <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn> -->
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
