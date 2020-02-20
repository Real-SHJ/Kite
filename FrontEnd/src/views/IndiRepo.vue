<template>
  <v-content>
    <div
      class="my-8"
      style="position: relative; height: 400px;"
    >
      <!-- <div style="background-color: black; opacity: 0.5; position: absolute; width: 100%; height: 400px; z-index: 1;"></div> -->
      <!-- <img src="https://cdn.pixabay.com/photo/2019/04/26/00/18/notebook-4156348__480.jpg" alt="" style="width: 100%; height: 100%; position: absolute;"> -->
      <img
        src="../assets/Main_Image.png"
        alt=""
        style="width: 100%; height: 100%; position: absolute;"
      >
      <!-- <v-col cols="3"> -->
      <!-- <h1 class="text-center" id="homebtn" >My Scrap Article</h1> -->
      <!-- </v-col> -->
    </div>
    <!-- ------------- 검색 기간 부분 --------------- -->
    <p class="header-title">
      <strong>My Scrap Article </strong>
    </p>
    <v-container>
      <v-row class="">
        <v-col
          cols="4"
          class="d-flex justify-center align-center"
        >
          <h3 style="margin-right: 5%">
            기업선택
          </h3>
          <v-overflow-btn
            v-model="choice_company"
            class=""
            :items="company_choice"
            label="기업선택"
            target="#dropdown-example"
          />
        </v-col>
        <v-col class="d-flex justify-center align-center">
          <h3 style="margin-right: 2%">
            검색기간
          </h3>
          <date-picker
            v-model="range"
            :lang="lang"
            range
            style="margin-right: 3%"
          />

          <v-btn
            depressed
            color="pink white--text"
            style="margin-right: 1%"
            @click="scrapreq"
          >
            검색
          </v-btn>
          <v-btn
            depressed
            color="blue white--text"
            style="margin-right: 1%"
            @click="scrapchoice"
          >
            전체조회
          </v-btn>
          <v-dialog
            v-model="dialog"
            scrollable=""
            max-width="1000"
          >
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
            <ShareReqPage />
          </v-dialog>
        </v-col>
      </v-row>
    </v-container>
    <v-divider class="mx-4" />
    <!-- ------------- 검색 기간 부분 --------------- -->
    <!--          작업   시  작 ------------------ -->
    <div class="team">
      <v-layout
        class="justify-center"
        row
        wrap
      />
      <v-container class="my-5">
        <v-layout
          row
          wrap
        >
          <!-- <v-flex xs12 sm6 md4 lg3 v-for="person in team" :key="person.name"> -->
          <!-- v-card-title class="text-md-center" -->
          <v-card
            v-if="articles.length === 0"
            flat
            class="mx-auto text-center"
            outlined
          >
            <h1>개인 저장소가 비어있어요</h1><h1>기사를 스크랩해보시겠어요?</h1>
            <v-card-actions class="d-flex justify-end">
              <WouldyouScrap />
            </v-card-actions>
          </v-card>
          <v-flex
            v-for="article in calData"
            :key="article.id"
            xs12
            sm6
            md6
            lg6
          >
            <v-card
              flat
              class="text ma-3"
              shaped=""
              raised=""
            >
              <div @click="goDetail(article)">
                <v-card-text>
                  <div class="subheading mb-4">
                    {{ article.company }}
                  </div>
                  <v-avatar
                    class="mb-4"
                    color="red lighten-4"
                    size="70"
                  >
                    <img
                      :src="company_image[article.company]"
                    >
                  </v-avatar>
                  <div
                    class="headline mb-5"
                    style="font-size: 20px;"
                  >
                    {{ article.title }}
                  </div>
                  <div
                    class="subheading mb-5"
                    style="font-size: 20px;"
                  >
                    {{ article.summary.slice(0, 100) }}...
                  </div>
                  <div class="subheading">
                    {{ article.newspaper }}
                  </div>
                </v-card-text>
              </div>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </div>
    <div class="text-xs-center my-4">
      <v-pagination
        v-model="curPageNum"
        :length="numOfPages"
        :total-visible="7"
      />
    </div>
    <v-divider class="mx-4" />
    <!-- ------------- 3. 내 키워드 기사 부분 start--------------- -->
    <v-container
      style="width:100%; background-color: #E4E8EF"
      fluid
    >
      <v-container>
        <v-row>
          <v-col>
            <KeywordWordCloud
              :info="info"
              @myKeyword="goAlert"
            />
          </v-col>
          <v-col>
            <v-card color="grey lighten-4">
              <v-card-title v-if="myKeyword">
                "{{ myKeyword }}" 키워드가 포함된 {{ choice_company }} 기사
              </v-card-title>
              <v-card-title v-else>
                워드클라우드에서 키워드를 선택하세요
              </v-card-title>
              <v-list>
                <v-card
                  v-for="article in keywordarticles"
                  :key="article.id"
                  class="mx-auto"
                  max-width="344"
                  outlined
                >
                  <div @click="goDetail(article)">
                    <v-list-item three-line>
                      <v-list-item-content>
                        <div class="overline mb-4">
                          {{ article.company }}
                        </div>
                        <v-list-item-title class="headline mb-1">
                          {{ article.title }}
                        </v-list-item-title>
                        <v-list-item-subtitle>{{ article.summary }}</v-list-item-subtitle>
                      </v-list-item-content>
                      <v-avatar
                        color="red lighten-4"
                        size="70"
                      >
                        <img :src="company_image[article.company]">
                      </v-avatar>
                    </v-list-item>
                  </div>
                </v-card>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-container>
  </v-content>
</template>

<script>
import http from '../http-common'
import WouldyouScrap from '../components/WouldyouScrap.vue'
import DatePicker from 'vue2-datepicker'
import 'vue2-datepicker/scss/index.scss'
import KeywordWordCloud from '../components/KeywordWordCloud.vue'
import ShareReqPage from '../components/ShareReqPage.vue'

export default {
  name: 'Indirepo',
  components: {
    ShareReqPage,
    WouldyouScrap,
    DatePicker,
    KeywordWordCloud
  },
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
        // pickers: ['next7days ', 'next30days', 'previous7days', 'previous30days' ],
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
      //   company_choice: ['삼성전자', 'LG전자', 'SK텔레콤', 'GS칼텍스', 'KT', '네이버', 'S-OIL', 'SK하이닉스',
      //                  '현대자동차', 'CJ제일제당', '국민은행', '포스코', '삼성SDS', '신한은행', '우리은행'],
      companylist: [],
      company_choice: [],
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
  watch: {
    company: {
      handler () {
        this.articles = []
        this.page = 1
      }
    }
  },
  mounted () {
    // this.getArticle()
    console.log(this.company_choice)
    // this.scrapreq()
    this.scrapchoice()
    this.init()
  },
  methods: {
    goAlert (key) {
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
      // let companylist = null
      this.company_choice = this.$session.get('my-info').companylist.split(',')
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
@font-face {
  font-family: 'LogoFont'; /* 폰트 패밀리 이름 주기*/
  src: url('../fonts/BLKCHCRY.TTF'); /*폰트 파일 주소*/
}
.header-title {
  /* position: absolute; */
  z-index: 2;
  /* top: 25%; */
  /* left: 31%; */
  margin-top: auto;
  font-size: 70px;
  /* font-weight: 400; */
  color: #000000;
  /* color: grey; */
  text-align: center;
  letter-spacing: -.05em;
}
</style>
