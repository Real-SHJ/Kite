<template>
    <v-content>
      <v-card max-width="500" class="mx-auto">
        <v-toolbar color="indigo" dark>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>

        <v-toolbar-title>Following</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn icon>
            <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
        </v-toolbar>
        <v-list>
            <v-list-item  v-for="(item, i) in items" :key="i">
                <v-list-item-avatar>
                    <v-img :src="item.profile"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
                <v-btn class="ma-2" v-if="!item.isFollow" small outlined color="indigo" @click="handleClick(i)">팔로잉</v-btn>
                <v-btn class="ma-2" v-else small outlined color="red" @click="handleClick(i)">팔로잉 취소</v-btn>
            </v-list-item>
        </v-list>
    </v-card>
  </v-content>
</template>

<script>
export default {
  name: 'following',
  data () {
    return {
      items: [
        { name: 'Sophia', profile: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', isFollow: false },
        { name: 'Olivia', profile: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', isFollow: false },
        { name: 'Emma', profile: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', isFollow: false },
        { name: 'Ava', profile: 'https://cdn.vuetifyjs.com/images/lists/4.jpg', isFollow: false },
        { name: 'Isabella', profile: 'https://cdn.vuetifyjs.com/images/lists/5.jpg', isFollow: false },
        { name: 'Aria', profile: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', isFollow: false },
        { name: 'Riley', profile: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', isFollow: false },
        { name: 'Amelia', profile: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', isFollow: false },
        { name: 'Mia', profile: 'https://cdn.vuetifyjs.com/images/lists/4.jpg', isFollow: false },
        { name: 'Layla', profile: 'https://cdn.vuetifyjs.com/images/lists/5.jpg', isFollow: false },
        { name: 'Jackson', profile: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', isFollow: false },
        { name: 'Liam', profile: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', isFollow: false },
        { name: 'Noah', profile: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', isFollow: false },
        { name: 'Aiden', profile: 'https://cdn.vuetifyjs.com/images/lists/4.jpg', isFollow: false },
        { name: 'Caden', profile: 'https://cdn.vuetifyjs.com/images/lists/5.jpg', isFollow: false },
        { name: 'Grayson', profile: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', isFollow: false },
        { name: 'Lucas', profile: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', isFollow: false },
        { name: 'Mason', profile: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', isFollow: false },
        { name: 'Oliver', profile: 'https://cdn.vuetifyjs.com/images/lists/4.jpg', isFollow: false },
        { name: 'Elijah', profile: 'https://cdn.vuetifyjs.com/images/lists/5.jpg', isFollow: false }
      ],
      followList: [
        {}
      ]
    }
  },
  methods: {
    handleClick: function (index) {
      var items = this.items
      for (let i = 0; i < items.length; i++) {
        if (i === index) {
          if (items[i].isFollow === false) { items[i].isFollow = true } else items[i].isFollow = false
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
