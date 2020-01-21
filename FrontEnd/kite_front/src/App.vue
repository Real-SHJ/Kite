<template>
  <div id="app">
    <Header/>
    <Menu/>
    <router-view/>
    <Footer/>
  </div>
</template>

<script>
import Header from './components/Header';
import Menu from './components/Menu';
import Footer from './components/Footer';

export default {

  components: {
    Header,
    Menu,
    Footer,
  },

};

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
