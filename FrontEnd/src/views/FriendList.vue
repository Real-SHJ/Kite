<template>
  <v-content>
    <v-list>
        <v-list-item  v-for="(item, i) in flist" :key="i">
            <v-list-item-avatar>
                <img v-if="item.image === 'null'" src="http://13.125.153.118:8999/img/tmp/tmp.jpeg"/>
                <v-img v-else :src="`http://13.125.153.118:8999/img/profile/${item.image}`"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title v-text="item.lastname + ' ' + item.firstname"></v-list-item-title>
            </v-list-item-content>
            <v-btn class="ma-2" small outlined color="green">친구</v-btn>
            <v-btn class="ma-2" small outlined color="red" @click="deletefriend(item.memberid)">친구 제거</v-btn>
        </v-list-item>
    </v-list>
  </v-content>
</template>

<script>
import router from '../router'
import http from '../http-common'
export default {
  name: 'friendlist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      flist: []
    }
  },
  methods: {
    deletefriend: function (friendid) {
      http
        .post('/member/deletefriend' + '/' + this.memberid + '/' + friendid)
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
        .get('/member/friendlist' + '/' + this.memberid)
        .then(
          response => {
            this.flist = response.data.flist
            console.log(response.data.message)
            console.log(this.flist)
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
