<template>
  <v-content>
    <v-card class="mx-auto">
        <v-container class="pa-1">
            <v-row class="fill-height" align="center" justify="center">
              <template v-for="(item, i) in items">
                <v-col :key="i" md="4">
                  <v-hover v-slot:default="{ hover }">
                    <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }" @click="handleClick(i)">
                      <v-img :src="item.src" height="100px" width="287px">
                        <v-card-title class="title white--text">
                          <v-row class="fill-height flex-column" justify="space-between">
                            <div class="text-right float-left">
                              <v-btn :class="{ 'show-btns': hover }" class="mx-2 clickbtn" fab dark small color="red" icon @click="handleClick(i)">
                                <v-icon v-if="!item.clicked" color="balck">{{ 'mdi-heart-outline' }}</v-icon>
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
        src: require('../assets/company_logo/국민은행.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/기업은행.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/삼성전자.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/삼성SDS.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/신한은행.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/우리은행.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/쿠팡.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/포스코.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/한국전력공사.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/현대자동차.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/CJ제일제당.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/GS칼텍스.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/KT.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/LG전자.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/NAVER.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/S-Oil.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/SK텔레콤.png'),
        clicked: false
      },
      {
        src: require('../assets/company_logo/SK하이닉스.png'),
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
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 1;
  position: absolute;
}
.v-card {
transition: opacity .2s ease-in-out;
}
.v-card:not(.on-hover) {
opacity: 0.75;
 }
</style>
