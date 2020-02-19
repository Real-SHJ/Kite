<template>
<v-container style="width:100%;">
  <div class="search" style="width:100%;">
  <div class="wrap">
  <div class="title_rank">분야별 인기 검색어</div>
  </div>
<v-carousel
    cycle
    height="670"
    hide-delimiters
    show-arrows-on-hover
    interval="4000"
  >
    <v-carousel-item
      v-for="(slide, i) in maninfo"
      :key="i"
    >
      <v-sheet
        :color="colors[6]"
        height="100%"
        width="100%"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
          style="center"
        >
        <v-card
              class="article-cards mx-auto my-10 cards"
              max-width="700"
              height="78%"
              align="center"
              :color="colors[0]"
            >
            <p style="color: black; font-size:1.5em; margin-top:5px" class="font-weight-black">{{maninfo[i][0]}}</p>
            <v-divider dark></v-divider>
            <v-list-item-group>
        <v-list-item  v-for="(elem,j) in maninfo[i][1]" :key="j">
        <v-list-item-content >
          <v-list-item-title @click="checkArticle(elem.company)" style="color: black" text-align="center">
            <span class="font-weight-black">{{j+1}} </span>
            {{elem.company}}
            </v-list-item-title>
        </v-list-item-content>
        </v-list-item>
            </v-list-item-group>
        </v-card>
        <v-card
              class="article-cards mx-auto my-10 cards"
              max-width="700"
              height="78%"
              align="center"
              :color="colors[0]"
            >
            <p style="color: black; font-size:1.5em; margin-top:5px" class="font-weight-black">{{girlinfo[i][0]}}</p>
            <v-divider dark></v-divider>
            <v-list-item-group>
        <v-list-item  v-for="(elem,j) in girlinfo[i][1]" :key="j">
        <v-list-item-content >
          <v-list-item-title @click="checkArticle(elem.company)" style="color: black" text-align="center"><span class="font-weight-black">{{j+1}} </span>  {{elem.company}}</v-list-item-title>
        </v-list-item-content>
        </v-list-item>
            </v-list-item-group>
        </v-card>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
  </div>
  <div class="rank">
  <RankChart :info ="info" />
  </div>
  <v-dialog v-model="dialog" max-width="290">
    <v-card>
      <v-card-title></v-card-title>
    <v-card-text class="">관심기사 등록으로 이동하시겠습니까?</v-card-text>
    <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="moveselectcompany">확인</v-btn>
        <v-btn color="green darken-1" text @click="dialog=false">취소</v-btn>
    </v-card-actions>
    </v-card>
  </v-dialog>
</v-container>
</template>

<script>
import http from '../http-common'
import RankChart from '../components/RankChart'
export default {
  name: 'searchrank',
  components: {
    RankChart
  },
  data () {
    return {
      colors: [
        'white',
        'indigo',
        'warning',
        'red lighten-3',
        'red lighten-1',
        'deep-purple accent-4',
        'green lighten-1'
      ],
      info: null,
      dialog: false,
      maninfo: [],
      girlinfo: []
    }
  },
  mounted () {
    this.init()
  },
  updated () {
  },
  methods: {
    moveselectcompany () {
      this.$router.push({ name: 'selectcompany' })
    },
    checkArticle (company) {
      console.log('함수에 들어옴!!' + company)
      if (this.$session.get('my-info').companylist.includes(company)) {
        this.$router.push({ name: 'articlecorpo', params: { 'company': company } })
      } else {
        // 관심리스트에 없으면
        this.dialog = true
      }
    },
    init () {
      http.get('/analysis/searchRank')
        .then(res => {
        // 토큰 저장
          // console.log('info에 데이터 저장')
          let info = res.data
          this.info = info
          const retarr = Object.keys(info).map(function (key) {
            return [key, info[key]]
          })
          for (var i = 0; i < retarr.length; i++) {
            if (i % 2 === 0) {
              this.girlinfo.push(retarr[i])
            } else {
              this.maninfo.push(retarr[i])
            }
          }
        })
    }
  }
}
</script>
<style>
.search{
  display:inline-block;
  width: 40%;
  height: 100%;
  margin-right: 30px;
  padding: 10px
}
.rank{
  display:inline-block;
  width: 50%;
  height: 100%;
  margin-left: 30px;
  padding: 10px
}
.cards{
  width:40%;
}
.wrap{
  font-size: 24px;
  margin-bottom: 3%;
}
.wrap>.title3{
  font-size: 0.9375em;
}
</style>
