<template>
  <!-- <v-app id="inspire" style="background-color: #FAFAFA"> -->
  <v-app
    id="inspire"
    style="background-color: #ffffff"
  >
    <!-- <v-content v-if="getOpen === false">
      <Opening @changeOpen="change"/>
    </v-content> -->
    <!-- <v-content v-else> -->
    <Header
      :offset-top="offsetTop"
      :authenticated-check="AuthenticatedCheck"
    />
    <LeftMenu v-if="AuthenticatedCheck" />
    <router-view />
    <!-- <Footer/> -->
    <v-layout
      id="nav"
      v-scroll="onScroll"
      align="center"
      justify="center"
    />
    <!-- </v-content> -->
  </v-app>
</template>

<script>
import Header from './components/Header'
import LeftMenu from './components/LeftMenu'
// import Footer from './components/Footer''
// import Opening from './components/Opening.vue'
// import Opening from './components/Opening2'

export default {
  name: 'App',
  components: {
    Header,
    LeftMenu
    // Footer,
    // Opening
  },
  data () {
    return {
      AuthenticatedCheck: this.$session.has('my-token'),
      offsetTop: 0,
      getOpen: false
    }
  },
  updated () {
    this.AuthenticatedCheck = this.$session.has('my-token')
  },
  mounted () {
    console.log(this.AuthenticatedCheck)
    this.AuthenticatedCheck = this.$session.has('my-token')
    this.openCheck()
    this.isLogin()
  },
  methods: {
    onScroll (e) {
      this.offsetTop = window.pageYOffset || document.documentElement.scrollTop
    },
    change (childOpen) {
      this.$session.set('changeOpen', childOpen)
      this.getOpen = childOpen
    },
    isLogin () {
      setTimeout(() => {
        if (this.AuthenticatedCheck) {
          const userEmail = this.$session.get('my-info').userEmail
          const userName = this.$session.get('my-info').userName
          const userid = this.$session.get('my-info').userid
          const userImage = this.$session.get('my-info').userImage
          const companylist = this.$session.get('my-info').companylist
          this.$store.dispatch('infoSave', {
            userEmail: userEmail, userName: userName, userid: userid, userImage: userImage, companylist: companylist
          })
        }
      }, 1000)
    },
    openCheck () {
      if (this.$session.has('changeOpen')) {
        this.getOpen = this.$session.get('changeOpen')
      }
    }
  }
}

</script>

<style>
@font-face {
  font-family: 'MainFont'; /* 폰트 패밀리 이름 주기*/
  src: url('./fonts/IropkeBatangM.woff'); /*폰트 파일 주소*/
}
#inspire { /* 위에서 font-face로 지정해놓은 font-family를 적용*/
  font-family:'MainFont';
}
</style>
