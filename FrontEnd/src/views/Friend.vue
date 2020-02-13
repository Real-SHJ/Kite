<template>
    <v-content>
        <v-row>
            <v-col cols="12" sm="6" offset-sm="3">
                <v-card max-width="500" class="mx-auto">
                    <v-card-title class="orange white--text">
                        <v-menu bottom right :open-on-hover="openOnHover" :close-on-content-click="closeOnContentClick">
                            <template v-slot:activator="{ on }">
                                <v-btn color="white" dark icon v-on="on">
                                    <v-icon>mdi-menu</v-icon>
                                </v-btn>
                            </template>
                            <v-list>
                                <v-list-item v-for="(item, i) in items" :key="i" @click="handleClick(i)">
                                    <v-list-item-icon>
                                        <v-icon>{{ item.icon }}</v-icon>
                                    </v-list-item-icon>
                                    <v-list-item-content>
                                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                        </v-menu>
                        <span class="headline">{{kindTitle}}</span>

                        <v-spacer></v-spacer>

                    </v-card-title>

                    <InsertFriendWait v-if="kind===1"/>
                    <Request v-else-if="kind===2"/>
                    <Response v-else-if="kind===3"/>
                    <FriendList v-else-if="kind===4"/>
                </v-card>
            </v-col>
        </v-row>
    </v-content>
</template>
<script>
import Request from '../components/Request.vue'
import Response from '../components/Response.vue'
import FriendList from '../components/FriendList.vue'
import InsertFriendWait from '../components/InsertFriendWait.vue'
export default {
  name: 'friend',
  components: {
    Request,
    Response,
    FriendList,
    InsertFriendWait
  },
  data: () => ({
    kindTitle: '친구 관리',
    kind: 1,
    openOnHover: false,
    closeOnContentClick: true,
    items: [
      { title: '회원 목록', icon: 'mdi-account-search' },
      { title: '친구 요청 관리', icon: 'mdi-account-arrow-right' },
      { title: '친구 응답 관리', icon: 'mdi-account-arrow-left' },
      { title: '친구 목록', icon: 'mdi-account-multiple' }
    ]
  }),
  methods: {
    handleClick (index) {
      this.kind = index + 1
      this.kindTitle = this.items[index].title
    }
  }
}
</script>
<style scoped>

</style>
