<template>
  <v-content>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          sm="3"
        >
          <v-menu
            open-on-hover
            bottom
            origin="center center"
            transition="scale-transition"
            :close-on-content-click="closeOnContentClick"
          >
            <template v-slot:activator="{ on }">
              <v-btn
                color="red"
                dark
                v-on="on"
              >
                색상 선택
              </v-btn>
            </template>

            <v-row>
              <v-col class="d-flex justify-center">
                <v-color-picker v-model="color" />
              </v-col>
            </v-row>
          </v-menu>
        </v-col>
        <v-col
          cols="12"
          sm="3"
        >
          <span>Highlight 기능</span>
          <v-btn @click="highlightOn()">
            On
          </v-btn>
          <v-btn @click="highlightOff()">
            Off
          </v-btn>
        </v-col>
        <v-col
          cols="12"
          sm="6"
        >
          <v-btn @click="save()">
            저장
          </v-btn>
        </v-col>
      </v-row>
      <v-spacer />
      <h1
        v-if="article"
        class="title"
      >
        {{ article.title }}
      </h1>
      <div class="d-flex justify-center">
        <img
          v-if="article.image"
          :src="article.image"
          width="50%"
          height="50%"
        >
      </div>
      <div
        v-if="article"
        id="maincontent"
        style="margin: 0%"
        v-html="article.content"
      />
    </v-container>
  </v-content>
</template>

<script>
import http from '../http-common'
export default {
  name: 'Test',
  props: {
    id: String
  },
  data () {
    return {
      article: null,
      type: 'hex',
      hex: '#FFFF00',
      closeOnContentClick: false
    }
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
  },
  mounted () {
    this.getArticle()
  },
  methods: {
    getArticle () {
      http
        .get(`/article/onescraparticle/${this.$session.get('my-info').userid}/${this.id}`)
        .then(
          response => {
            this.article = response.data.article
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
        item.style.cursor = 'pointer'
      }
    },
    highlightOff () {
      let col = document.querySelector(`#maincontent`)
      col.style.cursor = 'default'
      col.removeEventListener('click', this.actionHighlight)
      let cols = document.querySelectorAll('.high')
      for (let i = 0; i < cols.length; i++) {
        let item = cols[i]
        item.removeEventListener('click', this.removeItem)
        item.style.cursor = 'default'
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
      if (str.includes('<br>') || str.includes('</br>')) {
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
  }
}
</script>

<style scoped>
  @font-face {
    font-family: 'Noto Serif KR Bold', serif;
    src: url('../fonts/NotoSerifKR-Bold.otf');
  }
  .title {
    font-family: 'Noto Serif KR Bold' !important;
  }
  @font-face {
    font-family: 'Noto Serif KR Regular', serif;
    src: url('../fonts/NotoSerifKR-Regular.otf');
  }
  #maincontent {
    margin: 5%;
    padding: 5%;
    font-family: 'Noto Serif KR Regular' !important;
  }
</style>
