<template>
  <v-content>
    <v-toolbar color="#FBFAF5" class="" style="width: 100%; z-index: 1;">
      <v-row>
        <v-col cols="1">
          <v-btn text large id="homebtn" @click="goHome">Kite</v-btn>
        </v-col>
        <!-- <v-col cols="1"></v-col> -->
        <v-col class="d-flex align-center flex-row-reverse">
            <span class="toolbar-btn" @click="goIndiRepo">개인저장소</span>
            <span class="toolbar-btn" @click="goAnalysis">분석데이터</span>
            <span class="toolbar-btn" @click="goFriend">친구관리</span>
            <span class="toolbar-btn" @click="goSelectCom">관심기업추가</span>
          <div v-if="AuthenticatedCheck">
            <!-- <router-link class="black--text" to="/indirepository">개인저장소</router-link> |
            <router-link class="black--text" to="/analysis">분석데이터</router-link> |
            <router-link class="black--text" to="/friend">친구관리</router-link> |
            <router-link class="black--text" to="/selectcompany">관심기업 추가</router-link> | -->
            <span class="toolbar-btn" @click="logout">로그아웃</span>
          </div>
          <div v-else>
            <span class="toolbar-btn" @click="goStart">Start</span>
            <!-- <router-link class="black--text" to="/signup">로그인</router-link> -->
          </div>
        </v-col>
      </v-row>
    </v-toolbar>
    <v-lazy>
      <v-toolbar v-if="offsetTop > 350" color="#FBFAF5" id="pop-up-toolbar" class="" style="">
        <v-row>
          <v-col cols="1">
            <v-btn text large id="homebtn" @click="goHome">Kite</v-btn>
          </v-col>
          <!-- <v-col cols="3"></v-col> -->
          <v-col class="d-flex align-center flex-row-reverse">
            <span class="toolbar-btn" @click="goIndiRepo">개인저장소</span>
            <span class="toolbar-btn" @click="goAnalysis">분석데이터</span>
            <span class="toolbar-btn" @click="goFriend">친구관리</span>
            <span class="toolbar-btn" @click="goSelectCom">관심기업추가</span>
            <div class="float-right" v-if="AuthenticatedCheck">
              <!-- <router-link class="black--text" to="/indirepository">개인저장소</router-link> |
              <router-link class="black--text" to="/analysis">분석데이터</router-link> |
              <router-link class="black--text" to="/friend">친구관리</router-link> |
              <router-link class="black--text" to="/selectcompany">관심기업 추가</router-link> | -->
              <span class="toolbar-btn" @click="logout">로그아웃</span>
            </div>
            <div class="float-right" v-else>
              <span class="toolbar-btn" @click="goStart">Start</span>
              <!-- <router-link class="black--text" to="/signup">로그인</router-link> -->
            </div>
          </v-col>
        </v-row>
      </v-toolbar>
    </v-lazy>
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
    goIndiRepo () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/indirepo')
      } else {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.$router.push('/signup')
      }
    },
    goAnalysis () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/analysis')
      } else {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.$router.push('/signup')
      }
    },
    goFriend () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/friend')
      } else {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.$router.push('/signup')
      }
    },
    goSelectCom () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/selectcompany')
      } else {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.$router.push('/signup')
      }
    },
    goStart () {
      this.$router.push('/signup')
    },
    logout () {
      this.$store.dispatch('logout')
      this.$store.dispatch('infoDel')
      this.$session.destroy()
      this.$router.push('/signup')
    }
  },
  updated () {
    this.AuthenticatedCheck = this.$session.has('my-token')
  }
}
</script>

<style scoped>
.toolbar-btn:hover {
  cursor: pointer;
  font-size: 150%;
}
.toolbar-btn {
  font-size: 130%;
  margin-right: 10px;
  /* font-weight: bold; */
  font-family: 'Nanum Gothic', sans-serif;
}
#pop-up-toolbar {
  position: fixed;
  width: 100%;
  top: 0px;
  opacity: 0.9;
  z-index: 1;
}
@font-face {
  font-family: 'LogoFont'; /* 폰트 패밀리 이름 주기*/
  src: url('../fonts/BLKCHCRY.TTF'); /*폰트 파일 주소*/
}
#homebtn { /* 위에서 font-face로 지정해놓은 font-family를 적용*/
  font-family:'LogoFont' !important;
  background-color: #FBFAF5;
  color: #585022;
  font-size: 40px;
}
</style>
