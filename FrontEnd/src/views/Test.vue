<template>
  <v-content>
    <br/>
    <br/>
    <v-menu open-on-hover bottom origin="center center" transition="scale-transition" :close-on-content-click="closeOnContentClick">
      <template v-slot:activator="{ on }">
        <v-btn color="red" dark v-on="on">
          색상 선택
        </v-btn>
      </template>

      <v-row>
        <v-col class="d-flex justify-center">
          <v-color-picker v-model="color"></v-color-picker>
        </v-col>
      </v-row>
    </v-menu>

    <span style="margin-left:30px;">Highlight 기능</span>
    <v-btn @click="highlightOn()" style="margin-left:30px;">
        On
    </v-btn>
    <v-btn @click="highlightOff()">
        Off
    </v-btn>
    <v-btn @click="save()" style="margin-left:30px;">저장</v-btn>
    <v-spacer></v-spacer>
    <br/>
    <div v-if="article" v-html="article.content" id="maincontent"></div>
  </v-content>
</template>

<script>
import http from '../http-common'
export default {
  name: 'test',
  props: {
    id: String
  },
  methods: {
    getArticle () {
      http
        .get(`/article/onescraparticle/${this.$session.get('my-info').userid}/${this.id}`)
        .then(
          response => {
            this.article = response.data.article
            this.spanIndex = response.data.spanIndex
          }
        )
        .catch(err => console.log(err))
        .finally()
    },
    highlightOn () {
      let col = document.querySelector(`#maincontent`)
      col.style.cursor = 'url("http://13.125.153.118:8999/img/tmp/Highlighter.png"), auto'
      col.addEventListener('click', this.actionHighlight)
      let cols = document.querySelectorAll('.high')
      for (let i = 0; i < cols.length; i++) {
        let item = cols[i]
        item.addEventListener('click', this.removeItem)
      }
    },
    highlightOff () {
      let col = document.querySelector(`#maincontent`)
      col.removeEventListener('click', this.actionHighlight)
      let cols = document.querySelectorAll('.high')
      for (let i = 0; i < cols.length; i++) {
        let item = cols[i]
        item.removeEventListener('click', this.removeItem)
      }
    },
    actionHighlight () {
      this.message = window.getSelection()
      let str = this.message.toString()
      // eslint-disable-next-line camelcase
      let blank_pattern = /^\s+|\s+$/g
      if (str.replace(blank_pattern, '') === '') {
        return
      }
      this.replace(`<span class="high" style="background-color: ${this.color}; cursor: pointer">` + this.message.toString() + '</span>')

      let sel = window.getSelection ? window.getSelection() : document.selection
      if (sel) {
        if (sel.removeAllRanges) {
          sel.removeAllRanges()
        } else if (sel.empty) {
          sel.empty()
        }
      }
      let cols = document.querySelectorAll('.high')
      for (let i = 0; i < cols.length; i++) {
        let item = cols[i]
        item.addEventListener('click', this.removeItem)
      }
      // let col = document.querySelector(`#high${this.spanIndex}`)
      // col.addEventListener('click', function () {
      //   let val = this.innerHTML
      //   this.replaceWith(val)
      // })
      // this.spanIndex++
    },
    replace (text) {
      var _range = window.getSelection().getRangeAt(0)
      var _node = document.createElement('span')
      _node.innerHTML = text
      if (_node) _node = _node.childNodes[0]
      _range.deleteContents()
      _range.insertNode(_node)
    },
    removeItem (e) {
      let val = e.target.innerHTML
      e.target.replaceWith(val)
    },
    save () {
      var content = document.querySelector(`#maincontent`).innerHTML
      let fdata = new FormData()
      fdata.append('memberid', this.$session.get('my-info').userid)
      fdata.append('articleid', this.id)
      fdata.append('content', content)
      fdata.append('spanindex', this.spanIndex)
      http
        .put('/member/savecontent', fdata)
        .then(
          response => {
            console.log(response.data.message)
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    }
  },
  data () {
    return {
      spanIndex: 0,
      article: null,
      type: 'hex',
      hex: '#FFFF00',
      closeOnContentClick: false
    }
  },
  mounted () {
    this.getArticle()
  },
  computed: {
    color: {
      get () {
        return this[this.type]
      },
      set (v) {
        this[this.type] = v
      }
    },
    showColor () {
      if (typeof this.color === 'string') return this.color

      return JSON.stringify(Object.keys(this.color).reduce((color, key) => {
        color[key] = Number(this.color[key].toFixed(2))
        return color
      }, {}), null, 2)
    }
  }
}
</script>

<style scoped>
#maincontent {
    border-style: solid;
    border-width: 1px;
    border-color: blue;
    margin: 30px;
    padding: 30px;
}
</style>
