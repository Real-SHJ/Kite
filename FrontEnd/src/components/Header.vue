<template>
  <v-content>
    <v-toolbar color="" light="" class="" style="width: 100%; z-index: 1; ">
    <!-- <v-toolbar color="#cecece" opacity="0.1" class="" style="width: 100%; z-index: 1;"> -->
      <v-row>
        <v-col class="d-flex justify-end align-center" cols="5">
          <span class="toolbar-btn" @click="goSelectCom">관심기업추가</span>
          <span class="toolbar-btn" @click="goAnalysis">분석데이터</span>
        </v-col>
        <v-col cols="2">
          <v-col class="d-flex justify-center align-center">
            <v-btn text large id="homebtn" @click="goHome">Kite</v-btn>
          </v-col>
        </v-col>
        <v-col class="d-flex align-center ml-4">
            <span class="toolbar-btn" @click="goIndiRepo">개인저장소</span>
          <div v-if="AuthenticatedCheck">
            <Friend/>
            <!-- <span class="toolbar-btn" @click="goFriend">친구관리</span> -->
            <span class="toolbar-btn" @click="logout">로그아웃</span>
            <!-- <Friend/> -->
          </div>
          <div v-else>
            <span class="toolbar-btn" @click="goStart">Start</span>
            <v-snackbar
              v-model="snackbar"
              color="red"
              :timeout="timeout"
              :bottom="true"
              :right="true"
            >
              {{ text }}
              <v-btn
                color="white"
                text
                @click="snackbar = false"
              >
                FAIL
              </v-btn>
            </v-snackbar>
          </div>
        </v-col>
      </v-row>
    </v-toolbar>
    <v-lazy>
      <!-- <v-toolbar v-if="offsetTop > 350" color="#FBFAF5" id="pop-up-toolbar" class="" style="opacity: 0.8;"> -->
      <v-toolbar v-if="offsetTop > 350" dark="" id="pop-up-toolbar" class="" style="opacity: 0.8; z-index: 3;">
        <v-row>
          <v-col cols="1">
            <v-btn text large id="homebtn" @click="goHome">Kite</v-btn>
          </v-col>
          <!-- <v-col cols="3"></v-col> -->
          <v-col class="d-flex align-center flex-row-reverse">
            <span class="toolbar-btn" @click="goIndiRepo">개인저장소</span>
            <span class="toolbar-btn" @click="goAnalysis">분석데이터</span>
            <span class="toolbar-btn" @click="goSelectCom">관심기업추가</span>
            <v-snackbar
                v-model="snackbar"
                color="red"
                :timeout="timeout"
                :bottom="true"
                :right="true"
              >
                {{ text }}
                <!-- <v-btn
                  color="blue"
                  text
                  @click="addMem"
                >
                </v-btn> -->
                <v-btn
                  color="white"
                  text
                  @click="snackbar = false"
                >
                  FAIL
                </v-btn>
              </v-snackbar>
            <div class="float-right" v-if="AuthenticatedCheck">
              <Friend/>
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
import Friend from './Friend.vue'
export default {
  components: {
    Friend
  },
  data () {
    return {
      snackbar: false,
      text: '로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.',
      timeout: 1000
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
        // alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.snackbar = true
        setTimeout(() => {
          this.$router.push('/signup')
        }, this.timeout)
      }
    },
    goAnalysis () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/analysis')
      } else {
        // alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.snackbar = true
        setTimeout(() => {
          this.$router.push('/signup')
        }, this.timeout)
      }
    },
    goFriend () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/friend')
      } else {
        // alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.snackbar = true
        setTimeout(() => {
          this.$router.push('/signup')
        }, this.timeout)
      }
    },
    goSelectCom () {
      if (this.AuthenticatedCheck) {
        this.$router.push('/selectcompany')
      } else {
        // alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.snackbar = true
        setTimeout(() => {
          this.$router.push('/signup')
        }, this.timeout)
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
  font-size: 200%;
  /* text-decoration: underline; */
  /* text-decoration-color: rgb(255, 147, 97); */
  /* color: rgb(255, 147, 97); */
  color: #1d87be;
}
.toolbar-btn {
  font-size: 170%;
  margin-right: 25px;
  /* font-size: 25px; */
  /* font-weight: bold; */
  /* font-family: 'Nanum Gothic', sans-serif; */
}
.toolbar-btn-left {
  font-size: 170%;
  margin-right: 10px;
}
#pop-up-toolbar {
  position: fixed;
  width: 100%;
  top: 0px;
  opacity: 0.9;
  z-index: 1;
}
/* @font-face {
  font-family: 'LogoFont';
  src: url('../fonts/BLKCHCRY.TTF');
} */
#homebtn {
  /* font-family:'LogoFont' !important; */
  background-color: #ffffff;
  color: #000000;
  font-size: 40px;
  text-align: center
}
</style>
