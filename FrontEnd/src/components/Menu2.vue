<template>
  <v-layout class="layout">

      <v-btn class="mx-2" fixed fab dark color="teal" @mouseover="drawer = true">
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
          <v-list-item-title >John Leider</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
       <v-input @keyup="search" v-model="searchText" name="searchText">🔎
         <v-text-field placeholder="검색어를 입력해주세요"></v-text-field>
       </v-input>
      <v-divider></v-divider>
      <!-- 밑에 백엔드 연결할 것... -->
      <v-list dense >
        <v-list-item v-for="item in items" :key="item.title" link>
          <v-list-item-avatar >
            <v-img src="http://13.125.153.118:8999/img/logo/samsung.png" ></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title >{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
import http from '../http-common'
// import images from '../http-images'
export default {
  data () {
    return {
      drawer: null,
      picture: [],
      info: [],
      info2: [],
      items: [
        { title: '삼성전자', url: 'http://13.125.153.118:8999/img/logo/samsung.png' },
        { title: 'LG전자', url: 'http://13.125.153.118:8999/img/logo/LGElec.png' },
        { title: 'SK텔레콤', url: 'http://13.125.153.118:8999/img/logo/SKtelecom.png' },
        { title: 'GS칼텍스', url: 'http://13.125.153.118:8999/img/logo/GScaltex.png' },
        { title: 'KT', url: 'http://13.125.153.118:8999/img/logo/KT.png' },
        { title: '네이버', url: 'http://13.125.153.118:8999/img/logo/NAVER.png' },
        { title: 'S-OIL', url: 'http://13.125.153.118:8999/img/logo/S-OIL.png' },
        { title: 'SK하이닉스', url: 'http://13.125.153.118:8999/img/logo/SKhynix.png' },
        { title: '현대자동차', url: 'http://13.125.153.118:8999/img/logo/HyundaiCar.png' },
        { title: 'CJ제일제당', url: 'http://13.125.153.118:8999/img/logo/CJJJ.png' }
      ]
    }
  },
  mounted: {
  },
  methods: {
    init () {
      http
        .get('http:')
        .then(response => (this.info = response.data.resvalue))
        .catch(() => {
          this.errored = true
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
