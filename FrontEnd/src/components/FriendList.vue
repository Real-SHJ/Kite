<template>
  <v-list>
    <div v-if="flist.length">
      <v-list-item
        v-for="(item, i) in flist"
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
          @click="deletefriend(item)"
        >
          친구 제거
        </v-btn>
      </v-list-item>
    </div>
    <div v-else>
      <p
        class="my-3"
        style="font-size: 15px;"
      >
        친구 목록이 비었습니다...
      </p>
    </div>
    <v-snackbar
      v-model="snackbar"
      :timeout="timeout"
      :top="true"
      :right="true"
      color="red"
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
  name: 'Friendlist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      flist: [],
      snackbar: false,
      text: '친구가 삭제되었습니다.',
      timeout: 2000
    }
  },
  mounted () {
    this.getFriendList()
  },
  methods: {
    deletefriend: function (friend) {
      http
        .delete('/member/deletefriend' + '/' + this.memberid + '/' + friend.memberid)
        .then(
          response => {
            this.snackbar = true
            const target = this.flist.indexOf(friend)
            this.flist.splice(target, 1)
          }
        )
        .catch(err => console.log(err))
        .finally(
          // router.push('/friend')
        )
    },
    getFriendList () {
      http
        .get('/member/friendlist' + '/' + this.memberid)
        .then(
          response => {
            this.flist = response.data.flist
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
