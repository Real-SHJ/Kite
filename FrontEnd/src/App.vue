<template>
  <v-app>
    <div style="background-color: #F6F8FA">
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
    </div>
  </v-app>
</template>

<script>
import Header from './components/Header'
import Menu from './components/Menu2'
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
