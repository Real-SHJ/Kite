<template>
  <v-content>
      <div v-for="shareReq in shareReqs" :key="shareReq.id">
          <span>{{shareReq.title}}</span>
          <v-btn @click="scrapToMy">수락</v-btn>
          <v-btn>거절</v-btn>
      </div>
  </v-content>
</template>

<script>
// import http from '../http-common'
export default {
  name: 'shareReqPage',
  methods: {
  //   scrapToMy () {
  //     console.log('공유를 받아주마!!!!')
  //     http
  //       .post('!!!')
  //       .then((res) => {
  //         console.log(res)
  //       })
  //   },
  //   getReqList () {
  //     console.log('!!!!!!!!!!!!!!!')
  //     http
  //       .post('')
  //       .then((res) => {
  //         console.log(res)
  //       })
  //   }
  }
}
</script>

<style>

</style>
