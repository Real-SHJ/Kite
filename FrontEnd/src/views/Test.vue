<template>
  <v-content>
    <v-menu open-on-hover bottom origin="center center" transition="scale-transition">
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

    <!-- Trigger/Open The Modal -->
    <button id="myBtn">Open Modal</button>

    <!-- The Modal -->
    <div id="myModal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close">&times;</span>
        <p>Some text in the Modal..</p>
      </div>

    </div>
    <v-btn @click="save()">저장</v-btn>
    <p>Highlight</p>
    <v-btn @click="initMouse()">
        Off
    </v-btn>
    <v-btn @click="changeMouse()">
        On
    </v-btn>
    <v-spacer></v-spacer>
    <p>본문 내용</p>
    <div v-html="article" id="my"></div>
  </v-content>
</template>

<script>
import http from '../http-common'
export default {
  name: 'test',
  methods: {
    initMouse () {
      document.getElementById('my').style.cursor = 'default'
      let col = document.querySelector(`#my`)
      col.removeEventListener('click', this.getSelectText)
    },
    changeMouse () {
      document.getElementById('my').style.cursor = 'url("http://13.125.153.118:8999/img/tmp/Highlighter.png"), auto'
      let col = document.querySelector(`#my`)
      console.log(col)
      col.addEventListener('click', this.getSelectText)
    },
    getArticle () {
      http
        .get('/article/onearticle/15')
        .then(
          response => {
            this.article = response.data.article.content
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    },
    getSelectText () {
      this.message = window.getSelection()
      let str = this.message.toString()
      // eslint-disable-next-line camelcase
      let blank_pattern = /^\s+|\s+$/g
      if (str.replace(blank_pattern, '') === '') {
        return
      }
      this.replace(`<span id="high${this.spanIndex}" style="background-color: ${this.color}; cursor: pointer">` + this.message.toString() + '</span>')

      let sel = window.getSelection ? window.getSelection() : document.selection
      if (sel) {
        if (sel.removeAllRanges) {
          sel.removeAllRanges()
        } else if (sel.empty) {
          sel.empty()
        }
      }
      let col = document.querySelector(`#high${this.spanIndex}`)
      console.log(col)
      col.addEventListener('click', function () {
        let val = col.innerHTML
        col.replaceWith(val)
      })
      this.spanIndex++
    },
    replace (text) {
      var _range = window.getSelection().getRangeAt(0)
      var _node = document.createElement('span')
      _node.innerHTML = text
      if (_node) _node = _node.childNodes[0]
      _range.deleteContents()
      _range.insertNode(_node)
    },
    save () {
      var content = document.querySelector(`#my`)
      console.log(content.outerHTML)
      http
        .get('/member/test/' + this.company)
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
      article: '',
      type: 'hex',
      hex: '#FFFF00',
      company: '삼성전자'
    }
  },
  mounted () {
    this.getArticle()

    // Get the modal
    var modal = document.getElementById('myModal')

    // Get the button that opens the modal
    var btn = document.getElementById('myBtn')

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName('close')[0]

    // When the user clicks on the button, open the modal
    btn.onclick = function () {
      modal.style.display = 'block'
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
      modal.style.display = 'none'
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
      if (event.target === modal) {
        modal.style.display = 'none'
      }
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
  }
}
</script>

<style scoped>
p {
    font-weight: bold
}
#my {
    border-style: solid;
    border-width: 1px;
    border-color: blue;
    margin: 30px;
    padding: 30px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 50%; /* Could be more or less, depending on screen size */
}
/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}
.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}
</style>
