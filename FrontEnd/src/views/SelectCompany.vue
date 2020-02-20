<template>
  <v-content>
    <v-card class="mx-auto">
      <v-btn
        color="black"
        style="position:fixed; right:10px;"
        class="white--text"
        @click="saveCompany()"
      >
        관심 기업 저장
      </v-btn>
      <v-snackbar
        v-model="snackbar"
        color="green"
        :timeout="timeout"
        :top="true"
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
          SUCCESS
        </v-btn>
      </v-snackbar>
      <v-container class="pa-1">
        <v-row>
          <template v-for="(item, i) in items">
            <v-col
              :key="i"
              md="4"
            >
              <v-hover v-slot:default="{ hover }">
                <v-card
                  :elevation="hover ? 12 : 2"
                  :class="{ 'on-hover': hover }"
                  class="vcard"
                  @click="handleClick(i)"
                >
                  <v-row>
                    <v-col cols="2" />
                    <v-col
                      class="d-flex justify-end align-center"
                      cols="8"
                    >
                      <v-img :src="item.src" />
                    </v-col>
                    <v-col class="d-flex justify-center align-start">
                      <!-- <v-card-title class="title" align="right"> -->
                      <v-btn
                        class="clickbtn"
                        fab
                        small
                        color="red"
                        icon
                        @click="handleClick(i)"
                      >
                        <v-icon
                          v-if="!item.clicked"
                          color="red"
                        >
                          {{ 'mdi-heart-outline' }}
                        </v-icon>
                        <v-icon
                          v-else
                          color="red"
                        >
                          {{ 'mdi-heart' }}
                        </v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                  <!-- </v-card-title> -->
                </v-card>
              </v-hover>
            </v-col>
          </template>
        </v-row>
      </v-container>
    </v-card>
  </v-content>
</template>

<script>
import { mapGetters } from 'vuex'
import http from '../http-common'
export default {
  name: 'Selectcompany',
  data () {
    return {
      snackbar: false,
      text: '관심기업이 저장되었습니다.',
      timeout: 1000,
      items: [
        {
          name: '삼성전자',
          src: 'http://13.125.153.118:8999/img/logo/Samsung_Elec.svg',
          clicked: false
        },
        {
          name: '삼성SDS',
          src: 'http://13.125.153.118:8999/img/logo/Samsung_SDS.svg',
          clicked: false
        },
        {
          name: '네이버',
          src: 'http://13.125.153.118:8999/img/logo/Naver.svg',
          clicked: false
        },
        {
          name: '신한은행',
          src: 'http://13.125.153.118:8999/img/logo/Shinhan_Bank.svg',
          clicked: false
        },
        {
          name: '우리은행',
          src: 'http://13.125.153.118:8999/img/logo/Woori_Bank.svg',
          clicked: false
        },
        {
          name: '카카오',
          src: 'http://13.125.153.118:8999/img/logo/Kakao.svg',
          clicked: false
        },
        {
          name: '쿠팡',
          src: 'http://13.125.153.118:8999/img/logo/Coupang.svg',
          clicked: false
        },
        {
          name: '포스코',
          src: 'http://13.125.153.118:8999/img/logo/Posco.svg',
          clicked: false
        },
        {
          name: '하나은행',
          src: 'http://13.125.153.118:8999/img/logo/Hana_Bank.svg',
          clicked: false
        },
        {
          name: '한국전력공사',
          src: 'http://13.125.153.118:8999/img/logo/Korea_Elec.svg',
          clicked: false
        },
        {
          name: '현대모비스',
          src: 'http://13.125.153.118:8999/img/logo/Hyundai_Mobis.svg',
          clicked: false
        },
        {
          name: '현대자동차',
          src: 'http://13.125.153.118:8999/img/logo/Hyundai_Car.svg',
          clicked: false
        },
        {
          name: 'CJ제일제당',
          src: 'http://13.125.153.118:8999/img/logo/CJ_Cheiljedang.svg',
          clicked: false
        },
        {
          name: 'GS칼텍스',
          src: 'http://13.125.153.118:8999/img/logo/GS_Caltex.svg',
          clicked: false
        },
        {
          name: 'IBK기업은행',
          src: 'http://13.125.153.118:8999/img/logo/IBK_Bank.svg',
          clicked: false
        },

        {
          name: 'KB국민은행',
          src: 'http://13.125.153.118:8999/img/logo/KB_Bank.svg',
          clicked: false
        },
        {
          name: 'KT',
          src: 'http://13.125.153.118:8999/img/logo/KT.svg',
          clicked: false
        },
        {
          name: 'LG전자',
          src: 'http://13.125.153.118:8999/img/logo/LG_Elec.svg',
          clicked: false
        },
        {
          name: 'LG유플러스',
          src: 'http://13.125.153.118:8999/img/logo/LG_Uplus.svg',
          clicked: false
        },
        {
          name: 'LG화학',
          src: 'http://13.125.153.118:8999/img/logo/LG_Chemi.svg',
          clicked: false
        },
        {
          name: 'SK텔레콤',
          src: 'http://13.125.153.118:8999/img/logo/SK_Telecom.svg',
          clicked: false
        },
        {
          name: 'SK하이닉스',
          src: 'http://13.125.153.118:8999/img/logo/SK_Hynix.svg',
          clicked: false
        },
        {
          name: 'S-OIL',
          src: 'http://13.125.153.118:8999/img/logo/S-Oil.svg',
          clicked: false
        }
      ]
    }
  },
  computed: {
    ...mapGetters(['userInfo'])
  },
  mounted () {
    this.loadCompany()
  },
  methods: {
    handleClick: function (index) {
      var items = this.items
      for (let i = 0; i < items.length; i++) {
        if (i === index) {
          if (items[i].clicked === false) { items[i].clicked = true } else items[i].clicked = false
        }
      }
    },
    loadCompany: function () {
      setTimeout(() => {
        let companylist = this.$session.get('my-info').companylist.split(',')
        for (let i = 0; i < this.items.length; i++) {
          for (let j = 0; j < companylist.length; j++) {
            if (this.items[i].name === companylist[j]) this.items[i].clicked = true
          }
        }
      }, 1000)
    },
    saveCompany: function () {
      this.snackbar = true
      let companylist = ''
      var items = this.items
      for (let i = 0; i < items.length; i++) {
        if (items[i].clicked === true) {
          console.log(items[i].name)
          if (companylist.length === 0) {
            companylist = items[i].name
          } else {
            companylist = companylist + ',' + items[i].name
          }
        }
      }
      this.$store.dispatch('infoSave', { userEmail: this.$session.get('my-info').userEmail, userName: this.$session.get('my-info').userName, userid: this.$session.get('my-info').userid, userImage: this.$session.get('my-info').userImage, companylist: companylist })
      this.$session.set('my-info', { userEmail: this.$session.get('my-info').userEmail, userName: this.$session.get('my-info').userName, userid: this.$session.get('my-info').userid, userImage: this.$session.get('my-info').userImage, companylist: companylist })
      console.log(companylist)
      http
        .put('/member/updatecompany' + '/' + this.$session.get('my-info').userid + '/' + companylist)
        .then(
          response => {
            console.log(response.data.message)
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    }
  }
}
</script>

<style>
.vcard--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 1;
  position: absolute;
}
.vcard {
transition: opacity .2s ease-in-out;
}
.vcard:not(.on-hover) {
opacity: 0.75;
}
.clickbtn {
  color: red !important;
}
</style>
