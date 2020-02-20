<template>
  <v-footer
    dark
    padless
    app
    inset
  >
    <v-card
      class="flex"
      flat
      tile
      color="red lighten-2"
      :elevation="10"
    >
      <v-card-text class="py-2 white--text text-center">
        {{ new Date().getFullYear() }} - <strong>Wild Body</strong>
        <h5>김기범 &emsp; 서현준  원태희  정혜선  조 민</h5>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {

}
</script>

<style>

</style>
