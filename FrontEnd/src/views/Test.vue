<template>
    <v-content>
        <v-btn @click="changeMouse()" style="float:right">
            highlight
        </v-btn>
        <p >하이라이팅 한 내용</p>
        <p id="myp">{{message}}</p>
        <v-spacer></v-spacer>
        <p>본문 내용</p>
        <div v-html="article" id="my" @click="getSelectText()"></div>
    </v-content>
</template>

<script>
import http from '../http-common'
export default {
  name: 'test',
  methods: {
    changeMouse () {
      document.getElementById('my').style.cursor = 'url("http://13.125.153.118:8999/img/tmp/Highlighter.png"), auto'
    },
    getSelectText () {
      this.message = window.getSelection().toString()
      console.log(this.message)
      document.getElementById('myp').innerText = this.message
    },
    getArticle () {
      http
        .get('/article/info/12')
        .then(
          response => {
            this.article = response.data.article.content
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    }
  },
  data () {
    return {
      message: '이곳에 하이라이팅 된 부분이 보여집니다.',
      article: ''
    }
  },
  mounted () {
    this.getArticle()
  }
}
</script>

<style scoped>
p {
    font-weight: bold
}
#my {
    border-right-style: solid;
    border-width: 1px;
    border-color: blue;
    margin: 1px;
    padding: 1px;
}
</style>
