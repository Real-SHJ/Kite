<template>
  <v-dialog v-model="dialog" max-width="290">
    <template v-slot:activator="{ on }">
    <v-btn rounded class="mx-4" color="primary" v-on="on">
        <v-icon size="20px">fas fa-share-alt</v-icon>
    </v-btn>
    </template>
    <v-card
      max-width="500"
      class="mx-auto"
    >
    <br>
    <p class="text-center">친구를 선택해주세요.</p>
      <v-list>
        <v-list-item
          v-for="friend in myFriends"
          :key="friend.id"
          @click="shareArticle"
        >
          <v-list-item-icon>
          <v-icon color="pink">mdi-star</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
          <v-list-item-title v-text="friend.email"></v-list-item-title>
          </v-list-item-content>

          <v-list-item-avatar>
          <!-- <v-img :src="friend.image"></v-img> -->
          </v-list-item-avatar>
        </v-list-item>
      </v-list>
    </v-card>
  </v-dialog>
</template>

<script>
// import http from '../http-common'
export default {
  props: {
    article: Object,
    myFriends: Array
  },
  data () {
    return {
      items: [
        { icon: true, title: 'Jason Oner', avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg' },
        { title: 'Travis Howard', avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg' },
        { title: 'Ali Connors', avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg' },
        { title: 'Cindy Baker', avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg' }
      ],
      dialog: false
    }
  },
  methods: {
    shareArticle () {
      console.log('공유하겠습니다.')
    }
  },
  mounted () {
  }
}
</script>

<style>

</style>
