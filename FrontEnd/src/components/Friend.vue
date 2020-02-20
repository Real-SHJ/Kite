<template>
  <v-dialog
    v-model="dialog"
    scrollable=""
    max-width="400"
  >
    <template v-slot:activator="{ on }">
      <span
        class="toolbar-btn"
        v-on="on"
      >친구관리</span>
    </template>
    <v-card>
      <v-card-title class="black white--text py-3">
        <v-menu
          bottom
          right
          :open-on-hover="openOnHover"
          :close-on-content-click="closeOnContentClick"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              color="white"
              dark
              icon
              v-on="on"
            >
              <v-icon>mdi-menu</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item
              v-for="(item, i) in items"
              :key="i"
              @click="handleClick(i)"
            >
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-menu>
        <span class="headline">{{ kindTitle }}</span>
        <v-spacer />
      </v-card-title>
      <v-card-text style="height: 600px;">
        <InsertFriendWait v-if="kind===1" />
        <!-- <Request v-else-if="kind===2"/> -->
        <Response v-else-if="kind===2" />
        <FriendList v-else-if="kind===3" />
      </v-card-text>
      <v-divider />
      <div class="my-4 mr-3 d-flex flex-row-reverse">
        <v-btn
          color="green darken-1"
          style="font-size: 120%"
          text
          @click="dialog = false"
        >
          close
        </v-btn>
      </div>
    </v-card>
  </v-dialog>
</template>
<script>
// import Request from '../components/Request.vue'
import Response from '../components/Response.vue'
import FriendList from '../components/FriendList.vue'
import InsertFriendWait from '../components/InsertFriendWait.vue'
export default {
  name: 'Friend',
  components: {
    // Request,
    Response,
    FriendList,
    InsertFriendWait
  },
  data: () => ({
    dialog: false,
    kindTitle: '회원 목록',
    kind: 1,
    openOnHover: false,
    closeOnContentClick: true,
    items: [
      { title: '회원 목록', icon: 'mdi-account-search' },
      { title: '친구 요청 목록', icon: 'mdi-account-arrow-left' },
      { title: '친구 목록', icon: 'mdi-account-multiple' }
    //   { title: '친구 요청 관리', icon: 'mdi-account-arrow-right' },
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
.toolbar-btn:hover {
  cursor: pointer;
  /* font-size: 200%; */
  /* text-decoration: underline; */
  /* text-decoration-color: rgb(255, 147, 97); */
  /* color: rgb(255, 147, 97); */
  color: #1d87be;
}
.toolbar-btn {
  font-size: 170%;
  margin-right: 25px;
  /* font-size: 25px; */
  /* font-weight: bold; */
  /* font-family: 'Nanum Gothic', sans-serif; */
}
</style>
