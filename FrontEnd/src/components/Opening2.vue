<template>
  <div @click="click">
    <nav
      id="navbar"
      class="navbar"
    >
      <ul class="nav-menu">
        <li>
          <a
            data-scroll="home"
            href="#home"
            class="dot active"
          >
            <span>Home</span>
          </a>
        </li>
        <li>
          <a
            data-scroll="one"
            href="#one"
            class="dot"
          >
            <span>one</span>
          </a>
        </li>
        <li>
          <a
            data-scroll="two"
            href="#two"
            class="dot"
          >
            <span>two</span>
          </a>
        </li>
        <li>
          <a
            data-scroll="three"
            href="#three"
            class="dot"
          >
            <span>Three</span>
          </a>
        </li>
        <li>
          <a
            data-scroll="four"
            href="#four"
            class="dot"
          >
            <span>Four</span>
          </a>
        </li>
      </ul>
    </nav>
    <section id="one">
      <img src="../assets/two.jpg">
    </section>
    <section id="two">
      <img src="../assets/three.jpg">
    </section>
    <section id="three">
      <img src="../assets/one.jpg">
    </section>
    <section id="four">
      <v-carousel
        cycle
        hide-delimiter-background
        show-arrows-on-hover
        style="max-width:100%; height:auto; min-height:500"
      >
        <v-carousel-item
          v-for="(image,i) in images"
          :key="i"
        >
          <img :src="image">
        </v-carousel-item>
      </v-carousel>
    </section>
  </div>
</template>
<script>

export default {
  data () {
    return {
      images: {
        cmi1: require('../assets/cimg1.jpg'),
        cmi2: require('../assets/cimg2.jpg'),
        cmi3: require('../assets/cimg3.jpg'),
        cmi4: require('../assets/cimg4.jpg')
      },
      vimages: {
        img1: require('../assets/two.jpg'),
        img2: require('../assets/three.jpg'),
        img3: require('../assets/one.jpg')
      }
    }
  },
  methods: {
    click () {
      this.$router.push('/')
    }
  }
}
</script>
<style scoped>
section{
    width: 100%;
    /* height: 25%; */
    min-height: 700px;
}
.navbar {
    position: fixed;
    z-index: 999;
    top: 50%;
    right: 50px;
    transform: translateY(-50%);
}
</style>
