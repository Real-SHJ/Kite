<template>
  <v-content>
    <v-toolbar color="#FBFAF5" class="" style="width: 100%; z-index: 1;">
        <v-icon @click="goHome" style="margin-right: 1100px; margin-left: 20px;">home</v-icon>
        <div v-if="AuthenticatedCheck">
        <router-link class="black--text" to="/indirepository">IndiRepository</router-link> |
        <router-link class="black--text" to="/analysis">Analysis</router-link> |
        <router-link class="black--text" to="/friend">친구관리</router-link>
          <span @click="logout">Sign out</span>
        </div>
        <div v-else>
          <router-link class="black--text" to="/loginpage">Sign in</router-link> |
          <router-link class="black--text" to="/signup">Sign up</router-link>
          <router-link class="black--text" to="/signuptest">SignUpTest</router-link>
        </div>
    </v-toolbar>
    <v-toolbar v-if="offsetTop > 280" color="#FBFAF5" id="pop-up-toolbar" class="d-flex flex-row-reverse" style="">
      <v-icon @click="goHome">home</v-icon>
      <div v-if="AuthenticatedCheck">
      <router-link class="black--text" to="/indirepository">IndiRepository</router-link> |
      <router-link class="black--text" to="/analysis">Analysis</router-link> |
      <router-link class="black--text" to="/friend">친구관리</router-link>
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
      this.$store.dispatch('infoDel')
      this.$session.destroy()
      this.$router.push('/loginpage')
    }
  },
  updated () {
    this.AuthenticatedCheck = this.$session.has('my-token')
  }
}
</script>

<style>
#pop-up-toolbar {
  position: fixed;
  width: 100%;
  top: 0px;
  opacity: 0.8;
  z-index: 1;
}
.header-text {
    font-family: "Nanum Myeongjo",sans-serif;
}
.header-title {
  margin-top: 0;
  font-size: 40px;
  font-weight: 400;
  color: #1a1a1a;
  text-align: left;
  letter-spacing: -.05em;
}
.header-body {
    padding-bottom: 16px;
    margin-top: -3px;
    font-size: 32px;
    line-height: 46px;
    font-family: "Nanum Myeongjo",sans-serif;
    color: #cacaca;
    letter-spacing: -.05em;
}
</style>
