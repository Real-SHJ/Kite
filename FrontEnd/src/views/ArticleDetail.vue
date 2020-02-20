<template>
  <v-content>
    <v-container>
      <br>
      <br>
      <v-row>
        <v-col cols="2">
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
            <v-color-picker v-model="color" />
          </v-menu>
        </v-col>
        <v-col cols="4">
          <span style="margin-right: 4%; font-size: 120%; font-weight: bold;">Highlight 기능 :</span>
          <v-btn @click="highlightOn()">
            On
          </v-btn>
          <v-btn
            style="margin-left: 2%"
            @click="highlightOff()"
          >
            Off
          </v-btn>
        </v-col>
        <v-col cols="1">
          <v-btn @click="save()">
            저장
          </v-btn>
        </v-col>
        <v-col
          cols="5"
          class="d-flex justify-end"
        >
          <ScrapDialog :article="article" />
          <ShareDialog
            :article="article"
            :my-friends="myFriends"
          />
        </v-col>
      </v-row>
      <br>
      <br>
      <v-divider />
      <br>
      <br>
      <v-spacer />
      <p
        v-if="article"
        class="detail-title text-center"
      >
        {{ article.title }}
      </p>
      <p
        v-if="article"
        class="text-center"
      >
        기업: {{ article.company }}  언론사: {{ article.newspaper }}  posted: {{ article.publicationDate }}
      </p>
      <br>
      <br>
      <div class="d-flex justify-center">
        <img
          v-if="article.image !== null"
          :src="article.image"
          style="width: 50%; height: 50%"
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
import ScrapDialog from '../components/ScrapDialog.vue'
import ShareDialog from '../components/ShareDialog.vue'
export default {
  name: 'ArticleDetail',
  components: {
    ScrapDialog,
    ShareDialog
  },
  props: {
    id: String
  },
  data () {
    return {
      myFriends: [],
      article: null,
      type: 'hex',
      hex: '#FFFF00',
      closeOnContentClick: false,
      myarticleid: []
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
    this.getarticle()
    this.getMyFriends()
  },
  methods: {
    getarticle () {
      const myId = this.$session.get('my-info').userid
      http.get(`/article/onearticle/${myId}/${this.id}`)
        .then(res => {
          this.article = res.data.result
        })
        .catch(err => console.log(err))
    },
    init () {
      this.drawer = true
      let companylist = null
      companylist = this.userInfo.companylist
      this.info = companylist.split(',')
    },
    getMyFriends () {
      setTimeout(() => {
        if (this.$session.has('my-info')) {
          const myId = this.$session.get('my-info').userid
          http
            .get(`/member/friendlist/${myId}`)
            .then((res) => {
              this.myFriends = res.data.flist
            })
        }
      }, 1000)
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
      if (str.includes('\n')) {
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
      http
        .get('/member/getarticleid/' + this.$session.get('my-info').userid)
        .then(response => {
          this.myarticleid = response.data.result
          let flag = false
          for (let index = 0; index < this.myarticleid.length; index++) {
            if (Number(this.id) === this.myarticleid[index]) {
              flag = true
              break
            }
          }
          if (flag) {
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
          } else {
            alert('저장 기능을 사용하시려면 스크랩을 하셔야 합니다')
          }
        })
        .catch(err => console.log(err))
        .finally(
        )
    }
  }
}

</script>

<style scoped>
  .scrap {
    position: fixed;
    bottom: 20px;
    right: 100px;
  }
  .share {
    position: fixed;
    right: 20px;
    bottom: 20px;
  }
  @font-face {
    font-family: 'Noto Serif KR Bold', serif;
    src: url('../fonts/NotoSerifKR-Bold.otf');
  }
  .detail-title {
    font-size: 250%;
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
    font-size: 120%;
  }
</style>
