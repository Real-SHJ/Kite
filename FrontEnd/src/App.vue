<template>
  <v-content style="background-color: #F6F8FA">
    <div v-if="!getOpen">
      <Opening/>
    </div>
    <div v-else>
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
  </v-content>
</template>

<script>
import Header from './components/Header'
import Menu from './components/Menu2'
import Footer from './components/Footer'
import Opening from './components/Opening.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'index',
  components: {
    Header,
    Menu,
    Footer,
    Opening
  },
  data () {
    return {
      offsetTop: 0,
      isOpen: false
    }
  },
  methods: {
    onScroll (e) {
      this.offsetTop = window.pageYOffset || document.documentElement.scrollTop
    }
  },
  computed: {
    ...mapGetters([
      'getOpen'
    ])
  }
}

</script>

<style>
</style>
