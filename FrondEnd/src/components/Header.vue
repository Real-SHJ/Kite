<template>
  <v-content>
    <v-toolbar v-if="offsetTop < 480" color="#FBFAF5" class="d-flex flex-row-reverse" style="width: 100%; z-index: 1;">
      <router-link class="black--text" to="/">
      <v-icon>home</v-icon>
      Home
      </router-link>|
      <router-link class="black--text" to="/loginPage">Login</router-link>|
      <router-link class="black--text" to="/signUp">SignUp</router-link>
      <router-link class="black--text" to="/personaldatamodify">PersonalDataModify</router-link>
    </v-toolbar>
    <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"></v-parallax>
    <v-toolbar v-if="offsetTop > 480" color="#FBFAF5" class="d-flex flex-row-reverse" style="position: fixed; width: 100%; top: 0px; z-index: 1;">
      <router-link class="black--text" to="/">Home</router-link>|
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
  }
}
</script>

<style>
</style>
