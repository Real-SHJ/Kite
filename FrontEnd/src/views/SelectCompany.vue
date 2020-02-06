<template>
  <v-content>
    <v-card class="mx-auto">
        <v-container class="pa-1">
            <v-row class="fill-height" align="center" justify="center">
              <template v-for="(item, i) in items">
                <v-col :key="i" md="4">
                  <v-hover v-slot:default="{ hover }">
                    <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }" @click="handleClick(i)" class="vcard">
                      <v-img :src="item.src" height="100px" width="287px">
                        <v-card-title class="title">
                          <v-row class="fill-height flex-column" justify="space-between">
                            <div class="text-right float-left">
                              <v-btn class="clickbtn" fab small color="red" icon @click="handleClick(i)">
                                <v-icon v-if="!item.clicked" color="red">{{ 'mdi-heart-outline' }}</v-icon>
                                <v-icon v-else color="red">{{ 'mdi-heart' }}</v-icon>
                              </v-btn>
                            </div>
                          </v-row>
                        </v-card-title>
                      </v-img>
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
export default {
  name: 'selectcompany',
  data: () => ({
    items: [
      {
        src: 'http://13.125.153.118:8999/img/logo/CJ_Cheiljedang.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Coupang.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/GS_Caltex.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Hana_Bank.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Hyundai_Car.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Hyundai_Mobis.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/IBK_Bank.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Kakao.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/KB_Bank.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Korea_Elec.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/KT.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/LG_Chemi.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/LG_Elec.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/LG_Uplus.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Naver.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Posco.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Samsung_Elec.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Samsung_SDS.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Shinhan_Bank.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/SK_Hynix.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/SK_Telecom.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/S-Oil.svg',
        clicked: false
      },
      {
        src: 'http://13.125.153.118:8999/img/logo/Woori_Bank.svg',
        clicked: false
      }
    ]
  }),
  methods: {
    handleClick: function (index) {
      var items = this.items
      for (let i = 0; i < items.length; i++) {
        if (i === index) {
          if (items[i].clicked === false) { items[i].clicked = true } else items[i].clicked = false
        }
      }
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
