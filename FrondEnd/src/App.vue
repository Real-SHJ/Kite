<template>
  <v-app>
    <Header :offsetTop="offsetTop"/>
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
  </v-app>
</template>

<script>
import Header from './components/Header'
import Menu from './components/Menu'
import Footer from './components/Footer'

export default {

  components: {
    Header,
    Menu,
    Footer
  },
  data () {
    return {
      offsetTop: 0
    }
  },
  methods: {
    onScroll (e) {
      this.offsetTop = window.pageYOffset || document.documentElement.scrollTop
    }
  }
}

</script>

<style>
</style>