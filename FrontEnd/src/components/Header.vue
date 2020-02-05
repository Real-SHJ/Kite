<template>
  <v-content>
    <v-toolbar color="#FBFAF5" class="d-flex flex-row-reverse" style="width: 100%; z-index: 1;">
        <v-icon @click="goHome">home</v-icon>
        <div v-if="AuthenticatedCheck">
        <router-link class="black--text" to="/indirepository">IndiRepository</router-link> |
        <router-link class="black--text" to="/analysis">Analysis</router-link>
          <p @click="logout">Sign out</p>
        </div>
        <div v-else>
          <router-link class="black--text" to="/loginpage">Sign in</router-link> |
          <router-link class="black--text" to="/signup">Sign up</router-link>
        </div>
    </v-toolbar>
    <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg" style="height: 300px;"></v-parallax>
    <v-toolbar v-if="offsetTop > 280" color="#FBFAF5" class="d-flex flex-row-reverse" style="position: fixed; width: 100%; top: 0px; z-index: 1;">
      <v-icon @click="goHome">home</v-icon>
      <div v-if="AuthenticatedCheck">
      <router-link class="black--text" to="/indirepository">IndiRepository</router-link> |
      <router-link class="black--text" to="/analysis">Analysis</router-link>
        <p @click="logout">Sign out</p>
      </div>
      <div v-else>
        <router-link class="black--text" to="/loginpage">Sign in</router-link> |
        <router-link class="black--text" to="/signup">Sign up</router-link>
      </div>
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
    offsetTop: Number,
    AuthenticatedCheck: Boolean
  },
  methods: {
    goHome () {
      this.$router.push('/')
    },
    logout () {
      this.$store.dispatch('logout')
      this.$session.destroy()
      this.$router.push('/')
    }
  }
}
</script>

<style>
</style>
