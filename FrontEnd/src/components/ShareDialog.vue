<template>
  <v-dialog v-model="dialog" max-width="290">
    <template v-slot:activator="{ on }">
    <v-btn class="mx-4" color="primary" icon dark v-on="on">
        <v-icon size="24px">fas fa-share-alt</v-icon>
    </v-btn>
    </template>
    <v-card
      max-width="500"
      class="mx-auto"
    >
      <v-list>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          @click="shareArticle"
        >
          <v-list-item-icon>
          <v-icon v-if="item.icon" color="pink">mdi-star</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
          <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>

          <v-list-item-avatar>
          <v-img :src="item.avatar"></v-img>
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
    article: Object
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
  }
}
</script>

<style>

</style>
