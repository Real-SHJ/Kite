<template>
<v-container>
<v-carousel
    cycle
    height="700"
    hide-delimiters
    show-arrows-on-hover
  >
    <v-carousel-item
      v-for="(slide, i) in info"
      :key="i"
    >
      <v-sheet
        :color="colors[2]"
        height="100%"
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
              width="70%"
              height="78%"
              align="center"
              color="indigo"
            >
            <p class="display-1 font-weight-black">{{i}}</p>
            <v-list-item-group height="30%">
        <v-list-item v-for="(elem,j) in slide" :key="j">
        <v-list-item-content >
          <v-list-item-title text-align="center">{{j+1}}위 {{elem.company}}</v-list-item-title>
          <v-divider></v-divider>
        </v-list-item-content>
        </v-list-item>
            </v-list-item-group>
        </v-card>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
  <RankChart :info="info"/>
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
        'indigo',
        'warning',
        'red lighten-3',
        'red lighten-1',
        'deep-purple accent-4'
      ],
      info: null
    }
  },
  mounted () {
    this.init()
  },
  updated () {
  },
  methods: {
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
