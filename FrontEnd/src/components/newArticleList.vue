<template>
<div>
       <v-slide-group
       id="slidegrp"
        :continuous="true"
          :mandatory="mandatory"
          :show-arrows="showArrows"
          :center-active="centerActive"
      >
        <v-slide-item
          v-for="(article, i) in articles"
          :key="i"
        >
          <v-card
          class="img-wrapper"
          id="cards"
            tile
          >
            <v-img
            class="slideitem img-wrapper"
              :src="article.image"
              @click="clickArticle(article.articleid)"
              v-on:mouseover="hoverEvent"
              v-on:mouseout="outEvent"
            >

              <v-row
              class="slideitem"
              id="cardtitle"
              >
              {{article.title}}
              </v-row>
            <div class="darkness"></div>
            </v-img>
          </v-card>

        </v-slide-item>
      </v-slide-group>
  </div>
</template>

<script>
import http from '../http-common.js'

export default {
  data () {
    return {
      articles: []
    }
  },
  mounted () {
    http.get('/article/list').then(
      (res) => {
        this.articles = res.data.result
      }
    )
  },
  methods: {
    clickArticle (id) {
      this.$router.push('/articleDetail/' + id)
    },
    hoverEvent (event) {
      let target = event.target
      let first = target.firstChild
      let last = target.lastChild
      // console.log(target.firstChild)
      // console.log(target.lastChild)
      // target.style.boxShadow = '10px 20px 30px black'
      last.style.display = 'block'
      first.style.display = 'block'
    },
    outEvent (event) {
      let target = event.target
      target.style.boxShadow = 'none'
    }
  }
}

</script>

<style scoped>

#slidegrp {
  width:100%;
  height:600px;
}
.slideitem{
  height:100%;
  width:450px;
}
#cardtitle{
  margin:auto;
  width: 300px;
  text-overflow:ellipsis;
  white-space:nowrap;
  overflow:hidden;
  font-size:2.5em; color:white;
}
.darkness{
  background-color: black;
  display:none;
  width: inherit;
  height:  inherit;
  left:0;
  top:0;
  float: left;
}
</style>
