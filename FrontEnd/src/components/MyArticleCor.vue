<template>
  <div>
    <v-container class="px-2">
      <div
        v-for="article in corporations"
        :key="article.id"
        class="my-3"
      >
        <v-card
          class="article-cards mx-auto my-10"
          max-width="700"
          style="height: 600px;"
        >
          <div @click="goDetail">
            <v-img
              class="white--text align-end"
              height="400px"
              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
            >
              <v-avatar
                class="companyLogo"
                color="grey"
                size="100"
                tile
              >
                <v-img src="https://cdn.vuetifyjs.com/images/profiles/marcus.jpg" />
              </v-avatar>
            </v-img>
            <v-card-title>Top 10 Australian beaches{{ article }}</v-card-title>

            <v-card-subtitle class="pb-0 mb-5">
              Number 10
            </v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitehaven Beach</div>

              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>
          </div>
          <v-card-actions class="d-flex justify-end">
            <v-dialog
              v-model="dialog"
              max-width="290"
            >
              <template v-slot:activator="{ on }">
                <v-btn
                  class="mx-4"
                  color="primary"
                  icon
                  dark
                  v-on="on"
                >
                  <v-icon size="24px">
                    fas fa-cut
                  </v-icon>
                </v-btn>
              </template>
              <v-card>
                <v-card-title />
                <v-card-text class="">
                  이 기사를 스크랩 하시겠습니까?
                </v-card-text>
                <v-card-actions>
                  <v-spacer />
                  <v-btn
                    color="green darken-1"
                    text
                    @click="dialog = false"
                  >
                    Disagree
                  </v-btn>
                  <v-btn
                    color="green darken-1"
                    text
                    @click="dialog = false"
                  >
                    Agree
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-btn
              class="mx-4"
              icon
            >
              <v-icon size="24px">
                fas fa-share-alt
              </v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </v-container>
  </div>
</template>

<script>
import http from '../http-common'
export default {
  props: ['com'],
  data () {
    return {
      dialog: false,
      articles: [],
      corporations: [],
      company: this.com
    }
  },
  mounted () {
    this.getArticle()
  },
  updated () {
    this.getArticle()
  },
  methods: {
    goDetail () {
      this.$router.push('/ArticleDetail')
    },
    getArticle () {
      http.get('/article/list/')
        .then(res => {
        // 토큰 저장
          this.articles = res.data.resvalue
          let cidx = 0
          for (let idx = 0; idx < this.articles.length; idx++) {
            if (this.articles[idx]['company'] === this.company) {
              this.corporations[cidx++] = this.articles[idx]
            }
          }
        })
        .catch(err => console.log(err))
    }
  }
}

</script>

<style>
  .article-cards {
    position: relative;
  }
  .companyLogo {
    position: absolute;
    top: 0;
    left: 0;
  }
</style>
