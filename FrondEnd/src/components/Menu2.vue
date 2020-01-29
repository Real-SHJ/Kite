<template>
  <v-layout class="layout">

      <v-btn class="mx-2" fab dark color="teal" @mouseover="drawer = true">
      <v-icon dark>mdi-format-list-bulleted-square</v-icon>
    </v-btn>

    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
      @mouseleave.native="drawer = false"
    >
      <v-list-item>
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>John Leider</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item v-for="item in items" :key="item.title" link>
          <v-list-item-avatar>
            <v-img src="../assets/logo.png"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
export default {
  data () {
    return {
      drawer: null,
      items: [
        { title: '삼성전자', icon: '../assets/samsung.png' },
        { title: 'LG전자', icon: 'question_answer' }
      ]
    }
  }
}
</script>
<style>
.menu_img {
  height: 36px;
  width: 64px;
}
.btn_abc {
  margin-top: 200px;
  width: 50px;
}
.v-overlay {
  opacity: 0;
}
</style>
