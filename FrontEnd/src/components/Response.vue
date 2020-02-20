<template>
  <v-list>
    <div v-if="rslist.length !== 0">
      <v-list-item
        v-for="(item, i) in rslist"
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
          color="indigo"
          @click="insertfriend(item)"
        >
          수락
        </v-btn>
        <v-btn
          class="ma-2"
          small
          outlined
          color="red"
          @click="deletefriendwait(item)"
        >
          거절
        </v-btn>
      </v-list-item>
    </div>
    <div v-else>
      <p
        class="my-3"
        style="font-size: 15px;"
      >
        요청이 없습니다...
      </p>
    </div>
    <v-snackbar
      v-model="snackbar"
      :timeout="timeout"
      :top="true"
      :right="true"
      color="success"
    >
      {{ text }}
      <!-- <v-btn
        color="blue"
        text
        @click="addMem"
      >
      </v-btn> -->
      <v-btn
        color="white"
        text
        @click="snackbar = false"
      >
        SUCCESS
      </v-btn>
    </v-snackbar>
  </v-list>
</template>

<script>
// import router from '../router'
import http from '../http-common'
export default {
  name: 'Responselist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      requestid: this.$session.get('my-info').userid,
      rslist: [],
      snackbar: false,
      text: '친구가 성공적으로 등록되었습니다.',
      timeout: 2000
    }
  },
  mounted () {
    this.getFriendList()
  },
  methods: {
    deletefriendwait: function (response) {
      let requestid = response.memberid
      const userid = this.requestid
      http
        .delete('/member/deletefriendwait' + '/' + requestid + '/' + userid)
        .then(
          response => {
            const target = this.rslist.indexOf(response)
            this.rslist.splice(target, 1)
          }
        )
        .catch(err => console.log(err))
        .finally(
          // router.push('/friend')
        )
    },
    insertfriend: function (friend) {
      let requestid = friend.memberid
      let userid = this.requestid
      http
        .delete('/member/deletefriendwait' + '/' + requestid + '/' + userid)
        .then(
          response => {
            const target = this.rslist.indexOf(response)
            this.rslist.splice(target, 1)
          },
          http
            .post('/member/insertfriend' + '/' + this.memberid + '/' + requestid)
            .then(
              response => {
                this.snackbar = true
              }
            )
            .catch(err => console.log(err))
            .finally(
              // router.push('/friend')
            )
        )
        .catch(err => console.log(err))
        .finally(
          // router.push('/friend')
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
  }
}
</script>

<style scoped>

</style>
