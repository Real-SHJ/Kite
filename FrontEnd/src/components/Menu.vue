<template>
  <v-content>
    <transition name="toggle">
      <div
        v-if="fade"
        class="menu"
      >
        <dl>
          <dt id="logo">
            <div>KITE</div>
          </dt>
          <dt
            v-for="list in linklist"
            :key="list.link"
          >
            <router-link :to="list.link">
              {{ list.content }}
            </router-link>
          </dt>
          <dt id="company">
            <div>Welcome to Inventory</div>
            <div> Wonth</div>
          </dt>
        </dl>
      </div>
    </transition>
    <img
      id="menu_btn"
      src="../assets/hamburger.png"
      @click="enter"
    >
  </v-content>
</template>

<script>

export default {
  data () {
    return {
      fade: false,
      linklist: [
        { content: 'HOME', link: '/' },
        { content: 'MANAGER', link: '/manager' },
        { content: 'STATE', link: '/state' },
        { content: 'RESISTER', link: '/resister' }
      ]
    }
  },
  methods: {
    enter: function () {
      this.fade = !this.fade
    }
  }
}
</script>

<style>
  a{color:black}
  a:hover{color:rgb(255,98,124);}
  .menu{
    position:relative;
    top:0px;
    width:250px;
    height:1000px;
    background: rgba(98, 205, 255, 0.6);
    padding:40px 0;
    float:left
  }
  .toggle-enter-active, .toggle-leave-active{
    transition: all 1s
  }
  .toggle-enter, .toggle-leave-to{
    left:-250px;
    width:0px;
  }
  .toggle-enter-to, .toggle-leave{
    left:0px;
    width:250px;
  }
  .menu dl{padding:30px; position:fixed}
  .menu dl dt{ font-size:20px; height:75px;}
  .menu dl dt#logo {font-family: 'Passion One', cursive; font-size:45px; height:100px;}
  .menu dl dt#logo div{line-height:0.8}
  .menu dl dt#company {font-size:16px; margin-top:150px;}
  #menu_btn {position:fixed; top:490px; left:-30px; width:100px; height:100px; cursor:pointer}
</style>
