<template>
  <v-dialog
    v-model="dialog"
    max-width="290"
  >
    <template v-slot:activator="{ on }">
      <span
        class=""
        v-on="on"
      >
        {{ article.title.slice(0, 50) }}...
      </span>
    </template>
    <v-card>
      <!-- <v-card-title>{{article.articleid}}</v-card-title> -->
      <v-card-text class="pt-5">
        기사 요약
      </v-card-text>
      <v-card-text v-html="article.summary" />
      <v-card-actions>
        <v-spacer />
        <v-btn
          color="green darken-1"
          text
          @click="dialog = false"
        >
          close
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
export default {
  name: 'Sharereqs',
  props: {
    article: Object
  },
  data () {
    return {
      dialog: false
    }
  }
}
</script>

<style>

</style>
