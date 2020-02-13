<template>
  <v-content>
    <v-list>
        <v-list-item  v-for="(item, i) in nrlist" :key="i">
            <v-list-item-avatar>
                <img v-if="item.image === 'null'" src="http://13.125.153.118:8999/img/tmp/tmp.jpeg"/>
                <v-img v-else :src="`http://13.125.153.118:8999/img/profile/${item.image}`"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title v-text="item.lastname + ' ' + item.firstname"></v-list-item-title>
            </v-list-item-content>
            <v-btn class="ma-2" small outlined color="indigo" @click="insertfriendwait(item.memberid)">친구 요청</v-btn>
        </v-list-item>
    </v-list>
  </v-content>
</template>

<script>
import router from '../router'
import http from '../http-common'
export default {
  name: 'insertfriendwait',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      requestid: this.$session.get('my-info').userid,
      nrlist: []
    }
  },
  methods: {
    insertfriendwait: function (responseid) {
      http
        .post('/member/insertfriendwait' + '/' + this.requestid + '/' + responseid)
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
    getFriendList () {
      http
        .get('/member/norelationlist' + '/' + this.memberid)
        .then(
          response => {
            this.nrlist = response.data.nrlist
            console.log(response.data.message)
            console.log(this.nrlist)
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
