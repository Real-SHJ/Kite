<template>
  <v-layout class="layout">

    <v-btn class="mx-2" fixed fab dark color="teal" @mouseover='init'>
    <v-icon dark>mdi-format-list-bulleted-square</v-icon>
    </v-btn>

    <v-navigation-drawer
    class="nav-bar"
      v-model="drawer"
      fixed
      temporary
      width="350px"
      @mouseleave.native="drawer = false"
    >
      <v-list-item>
        <v-list-item-avatar>
          <!-- info.img를 추가 v-img에다가 "https:// /api/list-->
          <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content >
          <v-list-item-title >{{userInfo.userName}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
       <v-input @keyup.native="search" v-model="searchText" name="searchText">🔎
         <v-text-field placeholder="검색어를 입력해주세요"></v-text-field>
       </v-input>
      <v-divider></v-divider>
      <!-- 밑에 백엔드 연결할 것... -->
      <v-list dense >
        <v-list-item v-for="item in info" :key="item" link @click="goArtCorp">
          <v-list-item-avatar>
            <img :src="items[item]" />
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title >{{ item }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
import http from '../http-common'
import { mapGetters } from 'vuex'
// import images from '../http-images'
export default {
  data () {
    return {
      name: '',
      email: '',
      searchText: '',
      drawer: null,
      picture: [],
      info: [],
      info2: [],
      items: {
        'CJ제일제당': 'http://13.125.153.118:8999/img/logo/CJ_Cheiljedang.svg',
        '쿠팡': 'http://13.125.153.118:8999/img/logo/Coupang.svg',
        'GC칼텍스': 'http://13.125.153.118:8999/img/logo/GS_Caltex.svg',
        '하나은행': 'http://13.125.153.118:8999/img/logo/Hana_Bank.svg',
        '현대자동차': 'http://13.125.153.118:8999/img/logo/Hyundai_Car.svg',
        '현대모비스': 'http://13.125.153.118:8999/img/logo/Hyundai_Mobis.svg',
        'IBK기업은행': 'http://13.125.153.118:8999/img/logo/IBK_Bank.svg',
        '카카오': 'http://13.125.153.118:8999/img/logo/Kakao.svg',
        'KB국민은행': 'http://13.125.153.118:8999/img/logo/KB_Bank.svg',
        '한국전력공사': 'http://13.125.153.118:8999/img/logo/Korea_Elec.svg',
        'KT': 'http://13.125.153.118:8999/img/logo/KT.svg',
        'LG화학': 'http://13.125.153.118:8999/img/logo/LG_Chemi.svg',
        'LG전자': 'http://13.125.153.118:8999/img/logo/LG_Elec.svg',
        'LG유플러스': 'http://13.125.153.118:8999/img/logo/LG_Uplus.svg',
        '네이버': 'http://13.125.153.118:8999/img/logo/Naver.svg',
        '포스코': 'http://13.125.153.118:8999/img/logo/Posco.svg',
        '삼성전자': 'http://13.125.153.118:8999/img/logo/Samsung_Elec.svg',
        '삼성SDS': 'http://13.125.153.118:8999/img/logo/Samsung_SDS.svg',
        '신한은행': 'http://13.125.153.118:8999/img/logo/Shinhan_Bank.svg',
        'SK하이닉스': 'http://13.125.153.118:8999/img/logo/SK_Hynix.svg',
        'SK텔레콤': 'http://13.125.153.118:8999/img/logo/SK_Telecom.svg',
        'S-OIL': 'http://13.125.153.118:8999/img/logo/S-Oil.svg',
        '우리은행': 'http://13.125.153.118:8999/img/logo/Woori_Bank.svg'
      }
    }
  },
  computed: {
    ...mapGetters(['userInfo'])
  },
  mounted () {
  },
  methods: {
    init () {
      this.drawer = true
      var strArray // 시작하면 바로 정보 가져오기
      const fdata = new FormData()
      fdata.append('email', this.userInfo.userEmail)
      fdata.append('pw', this.userInfo.userPw)
      console.log(this.userInfo.userEmail)
      http
        .post('/member/info', fdata) // 회원 아이디 넣기
        .then(response => {
          strArray = response.data.result.company
          console.log(response)
          this.info = strArray.split(',')

          for (let i = 0; i < this.info.length; i++) {
            this.info[i] = this.info[i].replace(' ', '')
          }
          console.log(this.items[this.info[1]])
          console.log(this.info[0] + this.info[1])
        })
        .catch(() => {
          this.errored = true
          console.log(this.errored)
        })
        .finally(() => (this.loading = false))
    }
  },
  search () {
    http
      .get('/productinfo' + '/' + this.searchText) // rest에서 가져올 절대 주소 적기
      .then(response => {
        this.info = response.data.resvalue
        this.info2 = response.data.resvalue2
        for (let index = 0; index < this.info.length; index++) {
          this.info[index].img = '/' + this.info[index].img
        }
      })
      .catch(() => {
        this.errored = true
      })
      .finally(() => (this.loading = false))
  }
}
</script>
<style>
.menu_img {
  height: 36px;
  width: 64px;
}
.btn_abc {
  top: 200px;
  width: 50px;
}
.v-overlay {
  opacity: 0.1;
}
</style>
