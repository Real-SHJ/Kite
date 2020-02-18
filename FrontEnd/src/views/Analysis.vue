<template>
    <v-content>
      <v-tabs>
        <v-tab><router-link to="/searchrank" replace>검색어랭킹</router-link></v-tab>
      </v-tabs>
      <br>
      <v-dialog v-model="dialog" scrollable="" max-width="1000">
        <template v-slot:activator="{ on }">
          <v-btn
            class=""
            dark
            color="pink"
            v-on="on"
          >
            공유 요청함
          </v-btn>
        </template>
        <ShareReqPage/>
      </v-dialog>
    <v-layout justify-center row wrap>
    </v-layout>
    <!-- ------------- 검색 기간 부분 --------------- -->
      <v-container fluid>
          <v-row class="py-10">
          <h2><br>기업선택</h2>
            <v-col sm="2">
             <v-overflow-btn
                class="my-2"
                :items="company_choice"
                label="기업선택"
                target="#dropdown-example"
                v-model="choice_company"
                ></v-overflow-btn>
            </v-col>
            <v-btn depressed large color="pink white--text" @click="scrapreq">검색</v-btn>
          </v-row>
          </v-container>
    <!-- ------------- 검색 기간 부분 --------------- -->
    <!--          작업   시  작 ------------------ -->
    <div class="team">
        <!-- <h1 class="subheading greay--text">Article</h1> -->
        <v-layout row wrap>
            <v-card flat class="mx-auto text-center" raised="" color="blue lighten-4">
                <h1 class="text-center">My Scrap Article</h1>
            </v-card>
        </v-layout>
        <v-container class="my-5">
            <v-layout row wrap>
                <!-- <v-flex xs12 sm6 md4 lg3 v-for="person in team" :key="person.name"> -->
                    <!-- v-card-title class="text-md-center" -->
                    <v-card flat class="mx-auto text-center" outlined v-if="articles.length === 0">
                        <h1>개인 저장소가 비어있어요</h1><h1>기사를 스크랩해보시겠어요?</h1>
                        <v-card-actions class="d-flex justify-end">
                            <WouldyouScrap/>
                        </v-card-actions>
                    </v-card>
                <v-flex xs12 sm6 md6 lg6 v-for="article in calData" :key="article.id">
                    <v-card flat class="text ma-3" shaped="" raised="">
                        <div @click="goDetail(article)">
                        <v-card-text>
                            <div class="subheading mb-4">{{ article.company }}</div>
                            <v-avatar class="mb-4" color="red lighten-4" size="70">
                                <img
                                    :src="company_image[article.company]"/>
                            </v-avatar>
                            <div class="headline mb-4">{{ article.title }}</div>
                            <div class="subheading" v-html="article.summary"> </div>
                            <div class="subheading">{{ article.newspaper }}</div>
                        </v-card-text>
                        </div>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
    <div class="text-xs-center">
        <v-pagination :length="numOfPages" v-model="curPageNum" :total-visible="7">
        </v-pagination>
    </div>
      <v-container fluid>
      <v-layout >
        <v-row>
        <!-- ------------- 2. 워드 클라우드 부분 start --------------- -->
        <v-flex xs12 sm8 md8>
          <v-container fluid>
            <KeywordWordCloud @myKeyword="goAlert" :info ="info"/>
          </v-container>
        </v-flex>
        <!-- ------------- 2. 워드 클라우드 부분 end --------------- -->

        </v-row>
    <!-- ------------- 1. 전체 (워드 클라우드, 관련뉴스 부분) end --------------- -->
      </v-layout>
      </v-container>
    </v-content>
</template>
<script>
import ShareReqPage from '../components/ShareReqPage.vue'
import http from '../http-common'
import WouldyouScrap from '../components/WouldyouScrap.vue'
import 'vue2-datepicker/scss/index.scss'
import KeywordWordCloud from '../components/KeywordWordCloud.vue'
export default {
  name: 'analysis',
  data () {
    return {
      dialog: false,
      myKeyword: null,
      keywordarticles: [],
      info: null,
      page: 1,
      dataPerPage: 6,
      curPageNum: 1,
      company: null,
      choice_company: null,
      date: '',
      time: '',
      timePickerOptions: {
        start: '00:00',
        step: '00:30',
        end: '23:30'
      },
      datetime: '',
      range: '',
      shortcuts: [
        {
          text: 'Today',
          onClick: () => {
            this.range = [ new Date(), new Date() ]
          }
        }
      ],
      lang: {
        days: ['Sun', 'Mon', 'Tue', 'Wed', 'Thur', 'Fri', 'Sat'],
        months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Agu', 'Sep', 'Oct', 'Nov', 'Dec'],
        // pickers: ['next 7 days ', 'next 30 days selan', 'previous 7 days', 'previous 30 days' ],
        placeholderr: {
          date: 'Pilih Tanggal',
          dateRange: 'Pilih Range Tanggal'
        }
      },
      articles: [],
      team: [
        { name: 'Min', role: 'Developer' },
        { name: 'Kibeom', role: 'Developer' },
        { name: 'TH', role: 'Developer' },
        { name: 'HJ', role: 'Developer' },
        { name: 'HS', role: 'Developer' }
      ],
      page_items: [],
      fields: [
        { key: 'identifier', sortable: true },
        { key: 'name', sortable: true },
        { key: 'email', sortable: true },
        { key: 'isVerified', label: 'Verified', sortable: true },
        { key: 'isAdmin', label: 'Verified', sortable: true },
        { key: 'action' }
      ],
      isBusy: false,
      totalRows: 1,
      currentPage: 1,
      perPage: 15,
      company_choice: ['삼성전자', 'LG전자', 'SK텔레콤', 'GS칼텍스', 'KT', '네이버', 'S-OIL', 'SK하이닉스',
        '현대자동차', 'CJ제일제당', '국민은행', '포스코', '삼성SDS', '신한은행', '우리은행'],
      company_image: {
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
        'GC칼텍스': 'http://13.125.153.118:8999/img/logo/GS_Caltex.svg',
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
  components: {
    ShareReqPage,
    WouldyouScrap,
    KeywordWordCloud
  },
  methods: {
    goAlert (key) {
      alert(key)
      this.myKeyword = key
      this.getKeywordArticles()
    },
    goDetail (article) {
      this.$router.push({ path: `/test/${article.articleid}` })
    },
    wordClickHandler (name, value, vm) {
      console.log('wordClickHandler', name, value, vm)
    },
    getArticle () {
      console.log('기범아 왜 안 찍히냐고')
      http.get('article/list/')
        .then(res => {
          // 토큰 저장
          console.log(res.data.resvalue)
          this.articles = res.data.resvalue
          console.log(this.articles)
        })
        .catch(err => console.log(err))

      // this.getArticle()
    },
    scrapreq () {
    //   const fdata = new FormData()
      const memberid = this.$session.get('my-info').userid
      //   const email = this.$session.get('my-info').userEmail
      console.log('기범아 찍혔다니까')
      console.log(memberid)
      console.log(`/member/getScrap/${memberid}/${this.choice_company}`)
      http
        .get(`/member/getScrap/${memberid}/${this.choice_company}`)
      // .post(`/member/getScrap/${this.page}`, fdata)
        .then((res) => {
          console.log(res.data)
          this.articles = res.data.result
          console.log('들어왔다 데이터')
          console.log(this.articles[0])
        })
    },
    scrapchoice () {
      // 기업 선택해서 그 기업의 스크랩한 기사 조회
      console.log(this.choice_company)
      const memberid = this.$session.get('my-info').userid
      http
        .get(`/member/getScrap/${memberid}`)
      // .post(`/member/getScrap/${this.page}`, fdata)
        .then((res) => {
          console.log(res.data)
          this.articles = res.data.result
          console.log('들어왔다 데이터222')
          console.log(this.articles[0])
          console.log('삼성전자만 들어왔니?')
        })
    },
    setDate (newDate) {
      this.dateRange = newDate
      console.log(this.dateRange)
    },
    myProvider (ctx) {
      console.log(ctx)
    },
    init () {
      const memberid = this.$session.get('my-info').userid
      http
        .get('/member/getkeyword' + '/' + memberid)
        .then(
          response => {
            console.log(response.data.message)
            this.info = response.data.result
            console.log(this.info)
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    },
    getKeywordArticles () {
      http
        .get('/article/mykeywordarticle' + '/' + this.$session.get('my-info').userid + '/' + this.myKeyword)
        .then(
          response => {
            console.log(response.data.message)
            this.keywordarticles = response.data.result
            console.log(this.keywordarticles)
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    }
  },
  watch: {
    company: {
      handler () {
        this.articles = []
        this.page = 1
      }
    }
  },
  computed: {
    startOffset () {
      return ((this.curPageNum - 1) * this.dataPerPage)
    },
    endOffset () {
      return (this.startOffset + this.dataPerPage)
    },
    numOfPages () {
      return Math.ceil(this.articles.length / this.dataPerPage)
    },
    calData () {
      return this.articles.slice(this.startOffset, this.endOffset)
    }
  },
  mounted () {
    // this.getArticle()
    console.log(this.company_choice)
    // this.scrapreq()
    this.scrapchoice()
    this.init()
  }
}
</script>

<style scoped>
.basil {
  background-color: #FFFBE6 !important;
}
.basil--text {
  color: #356859 !important;
}
.content {
    height:100%;
    width: 100%;
    border: 2px solid #42b983;
    border-radius: 5px;
}
body
{
    background: #fff;
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    font-size: 14px;
    color:#000;
    margin: 0;
    padding: 0;
}
.swiper-container {
    width: 100%;
    padding-top: 50px;
    padding-bottom: 50px;
}
.swiper-slide {
    background-position: center;
    background-size: cover;
    width: 300px;
    height: 300px;

}
</style>
