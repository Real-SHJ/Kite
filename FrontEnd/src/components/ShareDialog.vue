<template>
  <v-dialog
    v-model="dialog"
    persistent=""
    scrollable=""
    max-width="400"
  >
    <template v-slot:activator="{ on }">
      <v-btn
        class="share"
        dark
        color="pink"
        style="margin-left: 1%"
        v-on="on"
      >
        <v-icon>fas fa-share-alt</v-icon>
      </v-btn>
    </template>
    <v-card
      style="height: 600px;"
      class="mx-auto"
    >
      <br>
      <p class="share-title text-center mb-2">
        친구를 선택해주세요.
      </p>
      <v-divider />
      <v-card-text>
        <FriendToShare
          v-for="friend in myFriends"
          :key="friend.id"
          :friend="friend"
          @shareReq="friendPlus"
          @cancelReq="friendPop"
        />
        <!-- <v-list-item
          v-for="friend in myFriends"
          :key="friend.id"
          @click="shareArticle(friend)"
        >
          <v-list-item-icon>
          <v-icon color="pink">mdi-star</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
          <v-list-item-title v-text="friend.email"></v-list-item-title>
          </v-list-item-content>

          <v-list-item-avatar>
          <v-img :src="friend.image"></v-img>
          </v-list-item-avatar>
        </v-list-item> -->
      </v-card-text>
      <v-alert
        :value="alert"
        text
        dense
        color="teal"
        border="left"
        transition="scale-transition"
      >
        <v-row>
          <v-col cols="6">
            <p>
              친구를 선택해주세요!!!!
            </p>
          </v-col>
          <v-col class="d-flex flex-row-reverse">
            <v-btn
              color="indigo"
              dark
              @click="alertClose"
            >
              close
            </v-btn>
          </v-col>
        </v-row>
      </v-alert>
      <v-card-actions>
        <v-spacer />
        <v-btn
          color="green darken-1"
          text
          @click="shareClose"
        >
          cancel
        </v-btn>
        <v-btn
          color="green darken-1"
          text
          @click="shareArticle"
        >
          보내기
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import FriendToShare from '../components/FriendToShare.vue'
import http from '../http-common'
export default {
  name: 'Sharedialog',
  components: {
    FriendToShare
  },
  props: {
    article: Object,
    myFriends: Array
  },
  data () {
    return {
      items: [
        { icon: true, title: 'Jason Oner', avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg' },
        { title: 'Travis Howard', avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg' },
        { title: 'Ali Connors', avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg' },
        { title: 'Cindy Baker', avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg' }
      ],
      dialog: false,
      shareTarget: [],
      alert: false
    }
  },
  mounted () {
  },
  methods: {
    // islogin () {
    //   if (!this.$session.has('my-info')) {
    //     alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
    //     this.$router.push('/signup')
    //   }
    // },
    friendPlus (memberid) {
      this.shareTarget.push(memberid)
    },
    friendPop (memberid) {
      this.shareTarget = this.shareTarget.filter(target => {
        return target !== memberid
      })
    },
    shareArticle () {
      if (this.shareTarget.length) {
        const fdata = new FormData()
        fdata.append('sendid', this.$session.get('my-info').userid)
        fdata.append('receivelist', this.shareTarget)
        fdata.append('articleid', this.article.articleid)
        http
          .post(`/member/sendmessage/`, fdata)
          .then((res) => {
            console.log(res)
          })
        this.$store.dispatch('colorChanger', 'switch')
        this.shareTarget = []
        this.dialog = false
      } else {
        this.alert = true
      }
    },
    shareClose () {
      this.shareTarget = []
      this.dialog = false
      this.$store.dispatch('colorChanger', 'switch')
    },
    alertClose () {
      this.alert = false
    }
  }
}
</script>

<style>
.share-title {
  font-size: 20px;
}
</style>
