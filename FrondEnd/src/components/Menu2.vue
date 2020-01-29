<template>
  <div>
    <v-container class="fill-height">
      <v-btn class="btn_abc" color="red" light @mouseover="drawer = true">
        <v-img
          class="menu_img"
          src="https://randomuser.me/api/portraits/men/78.jpg"
        ></v-img>
      </v-btn>
    </v-container>

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
            <v-img src="item.icon"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      drawer: null,
      items: [
        { title: "삼성전자", icon: "../assets/samsung.png" },
        { title: "LG전자", icon: "question_answer" }
      ]
    };
  }
};
</script>
<style>
.menu_img {
  height: 36px;
  width: 64px;
}
.btn_abc {
  margin-top: 400px;
  width: 50px;
}
.v-overlay {
  opacity: 0;
}
</style>
