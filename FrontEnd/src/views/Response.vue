<template>
    <v-content>
      <v-card max-width="500" class="mx-auto">
        <v-toolbar color="indigo" dark>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>

        <v-toolbar-title>friend</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn icon>
            <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
        </v-toolbar>
        <v-list>
            <v-list-item  v-for="(item, i) in rslist" :key="i">
                <v-list-item-avatar>
                    <img v-if="item.image === 'null'" src="http://13.125.153.118:8999/img/tmp/tmp.jpeg"/>
                    <v-img v-else :src="`http://13.125.153.118:8999/img/profile/${item.image}`"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title v-text="item.lastname + ' ' + item.firstname"></v-list-item-title>
                </v-list-item-content>
                <v-btn class="ma-2" small outlined color="indigo" @click="handleClick(i)">수락</v-btn>
                <v-btn class="ma-2" small outlined color="red" @click="handleClick(i)">거절</v-btn>
            </v-list-item>
        </v-list>
    </v-card>
  </v-content>
</template>

<script>
import http from '../http-common'
export default {
  name: 'responselist',
  data () {
    return {
      memberid: this.$session.get('my-info').userid,
      rslist: []
    }
  },
  methods: {
    handleClick: function (index) {
      // var items = this.items
      // for (let i = 0; i < items.length; i++) {
      //   if (i === index) {
      //     if (items[i].isFollow === false) { items[i].isFollow = true } else items[i].isFollow = false
      //   }
      // }
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
