<template>
  <v-dialog v-model="dialog" max-width="290">
    <template v-slot:activator="{ on }">
    <v-btn rounded class="mx-4" color="primary" v-on="on">
        <v-icon size="20px">fas fa-share-alt</v-icon>
    </v-btn>
    </template>
    <v-card
      max-width="500"
      class="mx-auto"
    >
    <br>
    <p class="text-center">친구를 선택해주세요.</p>
      <v-list>
        <FriendToShare v-for="friend in myFriends" :key="friend.id" @shareReq="friendPlus" @cancelReq="friendPop" :friend="friend"/>
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
      </v-list>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="dialog = false">cancel</v-btn>
        <v-btn color="green darken-1" text>보내기</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import FriendToShare from '../components/FriendToShare.vue'
import http from '../http-common'
export default {
  props: {
    article: Object,
    myFriends: Array
  },
  components: {
    FriendToShare
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
      shareTarget: []
    }
  },
  methods: {
    friendPlus (memberid) {
      this.shareTarget.push(memberid)
      console.log(this.shareTarget)
    },
    friendPop (memberid) {
      this.shareTarget = this.shareTarget.filter(target => {
        return target !== memberid
      })
      console.log(this.shareTarget)
    },
    shareArticle (friend) {
      if (this.$session.has('my-info')) {
        console.log('공유하겠습니다.')
        const fdata = new FormData()
        fdata.append('sendid', this.$session.get('my-info').userid)
        fdata.append('receiveid', friend.memberid)
        fdata.append('articleid', this.article.articleid)
        http
          .post(`/member/sendmessage/`, fdata)
          .then((res) => {
            console.log(res)
          })
      } else {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.')
        this.$router.push('/signup')
      }
    }
  },
  mounted () {
  }
}
</script>

<style>

</style>
