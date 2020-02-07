<template>
    <v-content>
      <v-card max-width="500" class="mx-auto">
        <v-toolbar color="indigo" dark>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>

        <v-toolbar-title>friend</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn icon>
            <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
        </v-toolbar>
        <v-list>
            <v-list-item  v-for="(mem, i) in memberlist" :key="i">
                <v-list-item-avatar>
                    <img v-if="mem.image === 'undefined'" src="http://13.125.153.118:8999/img/tmp/tmp.jpeg"/>
                    <v-img v-else :src="`http://13.125.153.118:8999/img/profile/${mem.image}`"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title v-text="mem.lastname + ' ' + mem.firstname"></v-list-item-title>
                </v-list-item-content>
                <v-btn class="ma-2" small outlined color="indigo" @click="handleClick(i)">친구 요청</v-btn>
                <v-btn class="ma-2" small outlined color="red" @click="handleClick(i)">요청 취소</v-btn>
                <!-- <v-btn class="ma-2" small outlined color="indigo" @click="handleClick(i)">수락</v-btn>
                <v-btn class="ma-2" small outlined color="red" @click="handleClick(i)">거절</v-btn>
                <v-btn class="ma-2" small outlined color="green" @click="handleClick(i)">친구</v-btn>
                <v-btn class="ma-2" small outlined color="red" @click="handleClick(i)">친구 제거</v-btn> -->
            </v-list-item>
        </v-list>
    </v-card>
  </v-content>
</template>

<script>
import http from '../http-common'
export default {
  name: 'friendlist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      memberlist: [],
      friendlist: [],
      requestlist: [],
      responselist: []
    }
  },
  methods: {
    handleClick: function (index) {
      var items = this.items
      for (let i = 0; i < items.length; i++) {
        if (i === index) {
          if (items[i].isFollow === false) { items[i].isFollow = true } else items[i].isFollow = false
        }
      }
    },
    getMemberList () {
      http
        .get('/member/list')
        .then(response => (this.memberlist = response.data['result']))
        .catch(err => console.log(err))
        .finally(
          this.memberlist.forEach(element => {
            console.log(element.memberid)
          })
        )
    },
    getFriendList () {
      http
        .get('/member/friendlist' + '/' + this.memberid)
        .then(response => (this.friendlist = response.data['result']))
        .catch(err => console.log(err))
        .finally(
        )
    },
    getRequestList () {
      http
        .get('/member/requestlist' + '/' + this.memberid)
        .then(response => (this.requestlist = response.data['result']))
        .catch(err => console.log(err))
        .finally(
        )
    },
    getResponseList () {
      http
        .get('/member/responselist' + '/' + this.memberid)
        .then(response => (this.responselist = response.data['result']))
        .catch(err => console.log(err))
        .finally(
        )
    }
  },
  mounted () {
    this.getMemberList()
    this.getFriendList()
    this.getRequestList()
    this.getResponseList()
  }
}
</script>

<style scoped>

</style>
