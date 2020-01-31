<template>
  <v-content>
    <v-toolbar v-if="offsetTop < 280" color="#FBFAF5" style="width: 100%; z-index: 1;">
      <div class="flex-row">
        <v-icon class="col" @click="goHome">home</v-icon>
        <span></span>
        <router-link class="black--text" to="/loginPage">Login</router-link>|
        <router-link class="black--text" to="/signUp">SignUp</router-link>
        <router-link class="black--text" to="/personaldatamodify">PersonalDataModify</router-link>
      </div>
    </v-toolbar>
    <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg" style="height: 300px;"></v-parallax>
    <v-toolbar v-if="offsetTop > 280" color="#FBFAF5" class="d-flex flex-row-reverse" style="position: fixed; width: 100%; top: 0px; z-index: 1;">
      <v-icon @click="goHome">home</v-icon>
      <router-link class="black--text" to="/loginPage">Login</router-link>|
      <router-link class="black--text" to="/signUp">SignUp</router-link>
      <router-link class="black--text" to="/personaldatamodify">PersonalDataModify</router-link>
    </v-toolbar>
  </v-content>
</template>

<script>
export default {
  data () {
    return {

    }
  },
  props: {
    offsetTop: Number
  },
  methods: {
    goHome () {
      this.$router.push('/')
    }
  }
}
</script>

<style>
</style>
