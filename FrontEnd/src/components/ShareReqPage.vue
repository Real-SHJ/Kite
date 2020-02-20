<template>
  <v-content>
    <v-card height="650">
      <v-list>
        <p
          class="share-req-title text-center mt-4"
          style="font-size: 35px;"
        >
          공유 요청 목록
        </p>
        <v-divider />
        <v-list-item
          v-for="(myreq, index) in calData"
          :key="index"
          three-line
          class="mx-3"
        >
          <v-row>
            <v-col
              cols="4"
              class="d-flex justify-center align-center"
            >
              <v-list-item-avatar>
                <img
                  :src="`http://13.125.153.118:8999/img/profile/${myreq.member.image}`"
                  alt=""
                >
              </v-list-item-avatar>
              <!-- <p>{{ myreq.member.image }}</p> -->
              <div class="text-center">
                <p>{{ myreq.member.lastname }}{{ myreq.member.firstname }}</p>
                <v-list-item-subtitle>{{ myreq.member.email }}</v-list-item-subtitle>
              </div>
            </v-col>
            <v-col
              cols="6"
              class="d-flex justify-center align-center"
            >
              <ShareReqs :article="myreq.article" />
            </v-col>
            <v-col class="d-flex align-center mr-2">
              <v-icon
                style="margin-right: 5%; font-size: 250%;"
                ripple
                @click="reqAccept(myreq)"
              >
                save
              </v-icon>
              <v-icon
                style="font-size: 250%;"
                ripple
                @click="reqDelete(myreq.sno)"
              >
                delete
              </v-icon>
            </v-col>
          </v-row>
        </v-list-item>
        <!-- <v-card
          v-for="(myreq, index) in calData"
          :key="index"
          class="mx-3 my-1"
        >
        <v-row>
          <v-col
            class="ml-2"
            cols="2"
          >
            <v-card-title>{{ myreq.member.lastname }}{{ myreq.member.firstname }}</v-card-title>
          </v-col>
          <v-col
            cols="8"
          >
            <ShareReqs :article="myreq.article"/>
          </v-col>
          <v-col class="d-flex flex-row-reverse mr-2">
            <v-icon ripple @click="reqAccept(myreq)">
              save
            </v-icon>
            <v-icon ripple @click="reqDelete(myreq.sno)">
              delete
            </v-icon>
          </v-col>
        </v-row>
        </v-card> -->
      </v-list>
      <v-divider />
      <v-pagination
        v-model="curPageNum"
        class="mt-2"
        :length="numOfPages"
      />
    </v-card>
  </v-content>
</template>

<script>
import ShareReqs from './ShareReqs.vue'
import http from '../http-common'
export default {
  name: 'Sharereqpage',
  components: {
    ShareReqs
  },
  data () {
    return {
      reqListData: [],
      dataPerPage: 5,
      curPageNum: 1
    }
  },
  computed: {
    startOffset () {
      return ((this.curPageNum - 1) * this.dataPerPage)
    },
    endOffset () {
      return (this.startOffset + this.dataPerPage)
    },
    numOfPages () {
      return Math.ceil(this.reqListData.length / this.dataPerPage)
    },
    calData () {
      return this.reqListData.slice(this.startOffset, this.endOffset)
    }
  },
  mounted () {
    this.getReqList()
  },
  methods: {
  //   scrapToMy () {
  //     console.log('공유를 받아주마!!!!')
  //     http
  //       .post()
  //       .then((res) => {
  //         console.log(res)
  //       })
  //   },
    getReqList () {
      console.log('!!!!!!!!!!!!!!!')
      http
        // .get(`/member/messagelist/${this.$session.get('my-info').userid}`)
        .get(`/member/messagelist/7`)
        .then((res) => {
          const memberlist = res.data.memberlist
          const articlelist = res.data.articlelist
          const sno = res.data.SNO
          for (let idx = 0; idx < res.data.memberlist.length; idx++) {
            this.reqListData.push({ sno: sno[idx], member: memberlist[idx], article: articlelist[idx] })
          }
          console.log(this.reqListData)
        })
    },
    reqAccept (myreq) {
      const fdata = new FormData()
      fdata.append('memberid', this.$session.get('my-info').userid)
      fdata.append('articleid', myreq.article.articleid)
      http
        .post('/member/insertscrap', fdata)
        .then(res => {
        })
        .catch(err => console.log(err))
      http
        .delete(`/member/deleteMessage/${myreq.sno}`)
        .then((res) => {
          this.reqListData = this.reqListData.filter(target => {
            return target.sno !== myreq.sno
          })
        })
    },
    reqDelete (sno) {
      http
        .delete(`/member/deleteMessage/${sno}`)
        .then((res) => {
          this.reqListData = this.reqListData.filter(target => {
            return target.sno !== sno
          })
        })
    }
  }
}
</script>

<style>

</style>
