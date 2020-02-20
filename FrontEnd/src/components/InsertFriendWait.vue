<template>
  <v-list>
    <v-list-item
      v-for="(item, i) in list"
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
      <div class="d-flex flex-row-reverse">
        <InsertFriendBtn
          :item="item"
          :check-list="checkList"
        />
      </div>
      <!-- <v-btn class="ma-2" small outlined color="indigo" @click="insertfriendwait(item.memberid)">친구 요청</v-btn>
      <v-snackbar
              v-model="snackbar"
              :timeout="timeout"
              :top="true"
              :right="true"
              color="success"
            >
              {{ text }}
              <v-btn
                color="white"
                text
                @click="snackbar = false"
              >
                SUCCESS
              </v-btn>
      </v-snackbar> -->
    </v-list-item>
  </v-list>
</template>

<script>
import InsertFriendBtn from './InsertFriendBtn.vue'
// import router from '../router'
import http from '../http-common'
export default {
  name: 'Insertfriendwait',
  components: {
    InsertFriendBtn
  },
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      requestid: this.$session.get('my-info').userid,
      list: [],
      checkList: [],
      dialog: false,
      snackbar: false,
      text: '친구 요청을 보냈습니다.',
      timeout: 2000
    }
  },
  mounted () {
    this.getFriendList()
  },
  methods: {
    // insertfriendwait: function (responseid) {
    //   http
    //     .post('/member/insertfriendwait' + '/' + this.requestid + '/' + responseid)
    //     .then(
    //       response => {
    //         this.snackbar = true
    //         console.log(response.data.message)
    //       }
    //     )
    //     .catch(err => console.log(err))
    //     .finally(
    //       router.push('/friend')
    //     )
    // },
    getFriendList () {
      http
        .get('/member/relationlist' + '/' + this.memberid)
        .then(
          response => {
            this.list = response.data.list
            this.checkList = response.data.checklist
          }
        )
        .catch(err => console.log(err))
    }
  }
}
</script>

<style scoped>

</style>
