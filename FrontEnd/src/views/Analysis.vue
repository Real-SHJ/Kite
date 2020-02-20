<template>
  <v-content>
    <p class="header-title">
      <strong>기업 분석 데이터</strong>
    </p>
    <searchRank />
    <!-- <v-divider class="mx-4"></v-divider> -->
    <v-container
      style="width:100%; background-color: #E4E8EF"
      fluid
    >
      <v-container>
        <div
          class="search"
          style="width:100%;"
        >
          <v-row class="">
            <div class="d-flex align-center">
              <h2 style="font-size: 25px; padding: 10px;">
                기업선택
              </h2>
            </div>
            <v-col sm="2">
              <v-overflow-btn
                v-model="choice_company"
                class=""
                :items="companylist"
                label="기업선택"
                target="#dropdown-example"
              />
            </v-col>
          </v-row>
          <!-- </v-container> -->
          <v-divider class="mx-4" />
          <!-- <v-container> -->
          <v-layout>
            <!-- <v-row> -->
            <v-flex
              xs12
              sm8
              md8
            >
              <v-container fluid>
                <CompanyKeyword
                  v-if="choice_company"
                  :keywordinfo="keywordinfo"
                  :choice_company="choice_company"
                  @myKeyword="goAlert"
                />
                <p v-else>
                  기업을 선택해 주세요
                </p>
              </v-container>
            </v-flex>
            <!-- </v-row> -->
            <v-flex
              xs12
              sm4
              md4
            >
              <v-container fluid>
                <v-card color="grey lighten-4">
                  <v-container>
                    <!-- <v-row dense> -->
                    <v-col cols="12">
                      <v-card-title v-if="myKeyword">
                        "{{ myKeyword }}" 키워드가 포함된 {{ choice_company }} 기사
                      </v-card-title>
                      <v-card-title v-else>
                        워드클라우드에서 키워드를 선택하세요
                      </v-card-title>
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
                    </v-col>
                  <!-- </v-row> -->
                  </v-container>
                </v-card>
              </v-container>
            </v-flex>
          </v-layout>
        </div>
      </v-container>
    </v-container>
  </v-content>
</template>
<script>
import http from '../http-common'
import CompanyKeyword from '../components/CompanyKeyword.vue'
import SearchRank from '../components/SearchRank.vue'
export default {
  name: 'Analysis',
  components: {
    SearchRank,
    CompanyKeyword
  },
  data () {
    return {
      myKeyword: null,
      keywordarticles: [],
      keywordinfo: null,
      choice_company: null,
      companylist: ['네이버', '삼성전자', '삼성SDS', '신한은행', '우리은행', '카카오', '쿠팡', '포스코', '하나은행', '한국전력공사', '현대모비스', '현대자동차', 'CJ제일제당', 'GS칼텍스', 'IBK기업은행', 'KB국민은행', 'KT', 'LG전자', 'LG유플러스', 'LG화학', 'SK텔레콤', 'SK하이닉스', 'S-OIL'],
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
  watch: {
    choice_company: function () {
      http
        .get('/member/getkeywordarticle' + '/' + this.choice_company)
        .then(
          response => {
            this.keywordinfo = response.data.result
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    }
  },
  methods: {
    goAlert (key) {
      this.myKeyword = key
      this.getKeywordArticles()
    },
    goDetail (article) {
      this.$router.push({ path: `/articleDetail/${article.articleid}` })
    },
    getKeywordArticles () {
      http
        .get('/article/companykeywordarticle' + '/' + this.myKeyword)
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

<style scoped>
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
.search{
  display:inline-block;
  width: 100%;
  height: 100%;
  margin-right: 30px;
  padding: 10px
}
</style>
