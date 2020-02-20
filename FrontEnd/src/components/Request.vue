<template>
  <v-content>
    <v-list>
      <v-list-item
        v-for="(item, i) in rqlist"
        :key="i"
      >
        <v-list-item-avatar>
          <img
            v-if="item.image === 'null'"
            src="http://13.125.153.118:8999/img/tmp/tmp.jpeg"
          >
          <v-img
            v-else
            :src="`http://13.125.153.118:8999/img/profile/${item.image}`"
          />
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title v-text="item.lastname + ' ' + item.firstname" />
        </v-list-item-content>
        <v-btn
          class="ma-2"
          small
          outlined
          color="red"
          @click="deletefriendwait(item.memberid)"
        >
          요청 취소
        </v-btn>
      </v-list-item>
    </v-list>
  </v-content>
</template>

<script>
import router from '../router'
import http from '../http-common'
export default {
  name: 'Requestlist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      requestid: this.$session.get('my-info').userid,
      rqlist: []
    }
  },
  mounted () {
    this.getFriendList()
  },
  methods: {
    deletefriendwait: function (responseid) {
      http
        .delete('/member/deletefriendwait' + '/' + this.requestid + '/' + responseid)
        .then(
          response => {
          }
        )
        .catch(err => console.log(err))
        .finally(
          router.push('/friend')
        )
    },
    getFriendList () {
      http
        .get('/member/requestlist' + '/' + this.memberid)
        .then(
          response => {
            this.rqlist = response.data.rqlist
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

</style>
