<template>
  <v-content>
    <v-btn
      v-if="btnSwitch"
      class="ma-2"
      small
      outlined
      color="indigo"
      @click="insertfriendwait(item.memberid)"
    >
      친구 요청
    </v-btn>
    <v-btn
      v-else
      class="ma-2"
      small
      outlined
      color="red"
      @click="deletefriendwait(item.memberid)"
    >
      응답 대기중
    </v-btn>
    <!-- <v-btn v-else class="ma-2" small outlined color="indigo" @click="insertfriendwait(item.memberid)">응답 대기중</v-btn> -->
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
  </v-content>
</template>

<script>
// import router from '../router'
import http from '../http-common'
export default {
  props: {
    item: Object,
    checkList: Array
  },
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      requestid: this.$session.get('my-info').userid,
      snackbar: false,
      btnSwitch: null,
      text: '친구 요청을 보냈습니다.',
      timeout: 2000
    }
  },
  mounted () {
    this.findCheck()
    // console.log(this.checkList)
  },
  methods: {
    insertfriendwait: function (responseid) {
      http
        .post('/member/insertfriendwait' + '/' + this.requestid + '/' + responseid)
        .then(
          response => {
            this.btnSwitch = !this.btnSwitch
            this.snackbar = true
          }
        )
        .catch(err => console.log(err))
        .finally(
          // router.push('/friend')
        )
    },
    deletefriendwait: function (responseid) {
      http
        .delete('/member/deletefriendwait' + '/' + this.requestid + '/' + responseid)
        .then(
          response => {
            this.btnSwitch = !this.btnSwitch
          }
        )
        .catch(err => console.log(err))
        .finally(
          // router.push('/friend')
        )
    },
    findCheck () {
      for (const check in this.checkList) {
        if (this.checkList[check].memberid === this.item.memberid) {
          this.btnSwitch = this.checkList[check].check
        }
      }
    }
  }
}
</script>

<style>

</style>
