<template>
<v-container>
  <div class="search">
<v-carousel
    cycle
    height="670"

    hide-delimiters
    show-arrows-on-hover
  >
    <v-carousel-item
      v-for="(slide, i) in info"
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
              class="article-cards mx-auto my-10"
              max-width="700"
              width="50%"
              height="78%"
              align="center"
              :color="colors[0]"
            >
            <p style="color: black; font-size:1.5em; margin-top:5px" class="font-weight-black">{{i}}</p>
            <v-divider dark></v-divider>
            <v-list-item-group>
        <v-list-item  v-for="(elem,j) in slide" :key="j">
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
  <RankChart :info ="info"/>
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
        'green'
      ],
      info: null,
      dialog: false
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
          console.log('info에 데이터 저장')
          this.info = res.data
          console.log(this.info)
        })
    }
  }
}
</script>
<style>
.search{
  display:inline-block;
  width: 45%;
  height: 50px;
  margin-right: 30px;
  padding: 10px
}
.rank{
  display:inline-block;
  width: 45%;
  height: 50px;
  margin-left: 30px;
  padding: 10px
}
</style>
