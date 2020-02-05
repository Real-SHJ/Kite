<template>
  <v-app id="inspire" style="background-color: #F6F8FA">
    <v-content v-if="getOpen === false">
      <Opening @changeOpen="change"/>
    </v-content>
    <v-content v-else>
      <Header :offsetTop="offsetTop" :AuthenticatedCheck="AuthenticatedCheck"/>
      <Menu/>
      <router-view/>
      <Footer/>
      <v-layout
        v-scroll="onScroll"
        align="center"
        justify="center"
        id="nav"
      >
      </v-layout>
    </v-content>
  </v-app>
</template>

<script>
import Header from './components/Header'
import Menu from './components/Menu2'
import Footer from './components/Footer'
import Opening from './components/Opening.vue'

export default {
  name: 'app',
  components: {
    Header,
    Menu,
    Footer,
    Opening
  },
  data () {
    return {
      AuthenticatedCheck: this.$session.has('my-token'),
      offsetTop: 0
    }
  },
  methods: {
    onScroll (e) {
      this.offsetTop = window.pageYOffset || document.documentElement.scrollTop
    },
    change (childOpen) {
      this.$store.commit('changeOpen', childOpen)
    }
  },
  computed: {
    getOpen () {
      return this.$store.getters.getOpen
    }
  }
}

</script>

<style>
</style>
