<template>
<v-content>
    <v-layout justify-center row wrap>
        <v-flex xs11 style="text-align: right;">
            <h1 class="display-2 font-weight-thin mb=3"> Friends Search </h1>
            <!-- <h4 class="subheading">Find what your missing</h4> -->
        </v-flex>

        <v-flex xs11>
            <v-layout align-center justify-end="" row wrap>
                <v-flex xs2>
                    <v-form >
                        <v-text-field outline label="Search for firends" append-icon="search">
                        </v-text-field>
                    </v-form>
                </v-flex>
            </v-layout>
        </v-flex>
    </v-layout>
    <!-- ------------- 검색 기간 부분 --------------- -->
      <v-container fluid>
          <v-row>
            <br>검색기간
            <v-col cols="11" sm="2">
              <v-menu
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="40"
                trasition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="date_search_start"
                    label="검색 시작일 - Calendar Click"
                    prepend-icon="event"
                    readonly
                    v-on="on"
                  > </v-text-field>
                </template>
                <v-date-picker v-model="date_search_start"
                  year-icon="mdi-calendar-blank"
                  prev-icon="mdi-skip-previous"
                  next-icon="mdi-skip-next"
                  @input="menu2 = false"> </v-date-picker>
              </v-menu>
            <v-spacer></v-spacer>
            </v-col>
            <br>~
            <v-col cols="11" sm="2">
              <v-menu
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="40"
                trasition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="date_search_end"
                    label="검색 마지막날 - Calendar Click"
                    prepend-icon="event"
                    readonly
                    v-on="on"
                  > </v-text-field>
                </template>
                <v-date-picker v-model="date_search_end"
                  year-icon="mdi-calendar-blank"
                  prev-icon="mdi-skip-previous"
                  next-icon="mdi-skip-next"
                  @input="menu2 = false"> </v-date-picker>
              </v-menu>
            <v-spacer></v-spacer>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              class="py-2"
            >
              <v-btn-toggle
                v-model="text"
                tile
                color="deep-purple accent-3"
                group
              >
                <v-btn value="all">
                  전체
                </v-btn>

                <v-btn value="oneday">
                  1일
                </v-btn>

                <v-btn value="oneweek">
                  1주
                </v-btn>

                <v-btn value="onemonth">
                  1개월
                </v-btn>

                <v-btn value="threemonth">
                  3개월
                </v-btn>

                <v-btn value="sixmonth">
                  6개월
                </v-btn>

                <v-btn value="oneyears">

                </v-btn>
              </v-btn-toggle>
              <v-btn depressed large color="pink white--text">검색</v-btn>
            </v-col>
          </v-row>
          </v-container>
    <!-- ------------- 검색 기간 부분 --------------- -->

    <!-- ------------- 워드 클라우드, 관련뉴스 부분 --------------- -->
      <v-container fluid>
      <v-layout >
        <!-- ------------- 워드 클라우드 부분 --------------- -->
        <v-flex xs12 sm6 md6>
          <v-container fluid>
            <br>기업 이슈 키워드
            <div id="wc">
              <wordcloud
              :data="defaultWords"
              nameKey="name"
              valueKey="value"
              :color="myColors"
              :showTooltip="true"
              :wordClick="wordClickHandler">
              </wordcloud>
            </div>
          </v-container>
        </v-flex>
        <!-- ------------- 워드 클라우드 부분 --------------- -->

        <!-- ------------- 최신뉴스, 관련뉴스 부분 --------------- -->
        <v-flex xs12 sm6 md6>
          <v-container fluid>
            <v-card color="basil">
              <v-tabs
                v-model="tab"
                background-color="transparent"
                color="basil"
                grow
              >
                <v-tab
                  v-for="item in items"
                  :key="item"
                >
                  {{ item }}
                </v-tab>
              </v-tabs>

              <v-tabs-items v-model="tab">
                <v-tab-item
                  v-for="item in items"
                  :key="item"
                >
                  <v-card
                    color="basil"
                    flat
                  >
                    <v-card-text>{{ text }}</v-card-text>
                  </v-card>
                </v-tab-item>
              </v-tabs-items>
                <!-- ---------- 카드 내부 기사 ----------- -->
              <v-container>
                <v-row dense>
                  <v-col cols="12">
                    <v-card
                      color="#385F73"
                      dark
                    >
                      <v-card-title class="headline">Unlimited music now</v-card-title>

                      <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle>

                      <v-card-actions>
                        <v-btn text>Listen Now</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-col>

                  <v-col
                    v-for="(item, i) in items2"
                    :key="i"
                    cols="12"
                  >
                    <v-card
                      :color="item.color"
                      dark
                    >
                      <div class="d-flex flex-no-wrap justify-space-between">

                        <v-avatar
                          class="ma-2"
                          size="125"
                          tile
                        >
                          <v-img :src="item.src"></v-img>
                        </v-avatar>
                        <div>
                          <v-card-title
                            class="headline"
                            v-text="item.title"
                          ></v-card-title>

                          <v-card-subtitle v-text="item.artist"></v-card-subtitle>
                        </div>

                      </div>
                    </v-card>
                  </v-col>
                </v-row>
              </v-container>
              <!-- ---------- 카드 내부 기사 ----------- -->
            </v-card>
          </v-container>
        </v-flex>
    <!-- ------------- 최신뉴스, 관련뉴스 부분 --------------- -->
      </v-layout>
      </v-container>
</v-content>
</template>

<script>
import wordcloud from 'vue-wordcloud'

export default {
  name: 'wc',
  components: {
    wordcloud
  },
  methods: {
    wordClickHandler (name, value, vm) {
      console.log('wordClickHandler', name, value, vm)
    }
  },
  data () {
    return {
      myColors: ['#f00000', '#0000ff', '#00f000', '#f000f0'],
      defaultWords: [{
        'name': '삼성전자',
        'value': 26
      },
      {
        'name': 'SSAFY',
        'value': 19
      },
      {
        'name': 'SK',
        'value': 18
      },
      {
        'name': '현대',
        'value': 16
      },
      {
        'name': 'LG',
        'value': 15
      },
      {
        'name': 'KT',
        'value': 15
      },
      {
        'name': '멀티캠퍼스',
        'value': 15
      },
      {
        'name': 'NCSoft',
        'value': 13
      },
      {
        'name': 'Nexon',
        'value': 10
      },
      {
        'name': 'play',
        'value': 6
      },
      {
        'name': 'play',
        'value': 6
      },
      {
        'name': 'play',
        'value': 6
      },
      {
        'name': 'play',
        'value': 6
      },
      {
        'name': 'play',
        'value': 6
      }
      ],
      tab: null,
      items: [
        '최신 뉴스', '관련 뉴스'
      ],
      text: '뉴스뉴스뉴스뉴스뉴스뉴스',
      items2: [
        {
          color: '#1F7087',
          src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
          title: 'Supermodel',
          artist: 'Foster the People'
        },
        {
          color: '#952175',
          src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
          title: 'Halcyon Days',
          artist: 'Ellie Goulding'
        }
      ]
    }
  }
}
</script>

<style>
.basil {
  background-color: #FFFBE6 !important;
}
.basil--text {
  color: #356859 !important;
}
</style>
