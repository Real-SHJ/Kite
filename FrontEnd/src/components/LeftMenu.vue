<template>
  <v-layout class="layout">

    <v-btn class="mx-2" fixed fab dark color="green" @mouseover='init'>
    <v-icon dark>mdi-format-list-bulleted-square</v-icon>
      <!-- <v-list-item-avatar>
          <v-img src="../../src/assets/hamburger.png" @mouseover='init'></v-img>
        </v-list-item-avatar> -->
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
          <v-img :src="`http://13.125.153.118:8999/img/profile/${userInfo.userImage}`"></v-img>
        </v-list-item-avatar>

        <v-list-item-content >
          <v-list-item-title >{{userInfo.userName}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
       <v-input>
         <v-icon>search</v-icon>
         <input @keyup="search" type="text" v-model="searchText"  name="searchText" class="form-control" placeholder="검색어를 입력하세요." value="{param.searchText}">
       </v-input>
      <v-divider></v-divider>
      <!-- 밑에 백엔드 연결할 것... -->
      <v-list dense >
        <v-list-item v-for="item in info" :key="item" link @click="goArtCorp(item)">
          <v-list-item-avatar width="100" height="40">
            <img :src="items[item]"/>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title >{{ item }}</v-list-item-title>
            <!-- <router-link :to="`/articlecorpo/${item}`">{{item}}</router-link> -->
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'leftmenu',
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
        '삼성전자': 'http://13.125.153.118:8999/img/logo/Samsung_Elec.svg',
        'LG전자': 'http://13.125.153.118:8999/img/logo/LG_Elec.svg',
        'SK텔레콤': 'http://13.125.153.118:8999/img/logo/SK_Telecom.svg',
        'GS칼텍스': 'http://13.125.153.118:8999/img/logo/GS_Caltex.svg',
        'KT': 'http://13.125.153.118:8999/img/logo/KT.svg',
        '네이버': 'http://13.125.153.118:8999/img/logo/Naver.svg',
        'S-OIL': 'http://13.125.153.118:8999/img/logo/S-Oil.svg',
        'SK하이닉스': 'http://13.125.153.118:8999/img/logo/SK_Hynix.svg',
        '현대자동차': 'http://13.125.153.118:8999/img/logo/Hyundai_Car.svg',
        'CJ제일제당': 'http://13.125.153.118:8999/img/logo/CJ_Cheiljedang.svg',
        '국민은행': 'http://13.125.153.118:8999/img/logo/KB_Bank.svg',
        '포스코': 'http://13.125.153.118:8999/img/logo/Posco.svg',
        '삼성SDS': 'http://13.125.153.118:8999/img/logo/Samsung_SDS.svg',
        '신한은행': 'http://13.125.153.118:8999/img/logo/Shinhan_Bank.svg',
        '쿠팡': 'http://13.125.153.118:8999/img/logo/Coupang.svg',
        '하나은행': 'http://13.125.153.118:8999/img/logo/Hana_Bank.svg',
        '현대모비스': 'http://13.125.153.118:8999/img/logo/Hyundai_Mobis.svg',
        'IBK기업은행': 'http://13.125.153.118:8999/img/logo/IBK_Bank.svg',
        '카카오': 'http://13.125.153.118:8999/img/logo/Kakao.svg',
        'KB국민은행': 'http://13.125.153.118:8999/img/logo/KB_Bank.svg',
        '한국전력공사': 'http://13.125.153.118:8999/img/logo/Korea_Elec.svg',
        'LG화학': 'http://13.125.153.118:8999/img/logo/LG_Chemi.svg',
        'LG유플러스': 'http://13.125.153.118:8999/img/logo/LG_Uplus.svg',
        '우리은행': 'http://13.125.153.118:8999/img/logo/Woori_Bank.svg'
      }
    }
  },
  computed: {
    ...mapGetters(['userInfo'])
  },
  methods: {
    init () {
      this.drawer = true
      let companylist = null
      console.log('leftmenu userImage:' + this.userInfo.userImage)
      companylist = this.userInfo.companylist
      console.log('leftmemu companylist:' + companylist)
      this.info = companylist.split(',')
    },
    search () {
      // 전부 들어간 회사명을 다로 저장해 둔다.
      console.log('!!' + this.searchText)
      if (this.searchText === '') {
        // 빈칸이 있는경우
        this.init()
        console.log('빈칸빈칸@##')
      } else {
        // 글자가 들어가있는 경우
        this.info2 = []
        console.log('@@' + this.searchText)
        for (let i = 0; i < this.info.length; i++) {
          if (this.info[i].match(this.searchText)) {
            console.log(this.info[i])
            this.info2.push(this.info[i])
          }
        }
        this.info2 = this.info.filter(item => {
          return item.indexOf(this.searchText) > -1
        })
        this.info = []
        this.info = this.info2.valueOf()
      }
    },
    goArtCorp (item) {
      alert('클릭!!!' + item)
      this.$router.push({ name: 'articlecorpo', params: { 'company': item } })
    }
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
