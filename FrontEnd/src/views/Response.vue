<template>
  <v-content>
    <v-list>
        <v-list-item  v-for="(item, i) in rslist" :key="i">
            <v-list-item-avatar>
                <img v-if="item.image === 'null'" src="http://13.125.153.118:8999/img/tmp/tmp.jpeg"/>
                <v-img v-else :src="`http://13.125.153.118:8999/img/profile/${item.image}`"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title v-text="item.lastname + ' ' + item.firstname"></v-list-item-title>
            </v-list-item-content>
            <v-btn class="ma-2" small outlined color="indigo" @click="insertfriend(item.memberid)">수락</v-btn>
            <v-btn class="ma-2" small outlined color="red" @click="deletefriendwait(item.memberid)">거절</v-btn>
        </v-list-item>
    </v-list>
  </v-content>
</template>

<script>
import router from '../router'
import http from '../http-common'
export default {
  name: 'responselist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      requestid: this.$session.get('my-info').userid,
      rslist: []
    }
  },
  methods: {
    deletefriendwait: function (responseid) {
      let requestid = responseid
      responseid = this.requestid
      http
        .delete('/member/deletefriendwait' + '/' + requestid + '/' + responseid)
        .then(
          response => {
            console.log(response.data.message)
          }
        )
        .catch(err => console.log(err))
        .finally(
          router.push('/friend')
        )
    },
    insertfriend: function (friendid) {
      let requestid = friendid
      let responseid = this.requestid
      http
        .delete('/member/deletefriendwait' + '/' + requestid + '/' + responseid)
        .then(
          response => {
            console.log(response.data.message)
          },
          http
            .post('/member/insertfriend' + '/' + this.memberid + '/' + friendid)
            .then(
              response => {
                console.log(response.data.message)
              }
            )
            .catch(err => console.log(err))
            .finally(
              router.push('/friend')
            )
        )
        .catch(err => console.log(err))
        .finally(
          router.push('/friend')
        )
    },
    getFriendList () {
      http
        .get('/member/responselist' + '/' + this.memberid)
        .then(
          response => {
            this.rslist = response.data.rslist
          }
        )
        .catch(err => console.log(err))
        .finally(
        )
    }
  },
  mounted () {
    this.getFriendList()
  }
}
</script>

<style scoped>

</style>
