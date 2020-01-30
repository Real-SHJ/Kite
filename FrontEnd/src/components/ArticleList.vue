<template>
  <div>
    <v-container class="px-2">
        <div v-for="cardNumber in cardNumbers" :key="cardNumber.id" class="my-3">
            <v-card
              class="mx-auto my-10"
              max-width="700"
              style="height: 600px;"
            >
              <div @click="goDetail">
                <v-img
                  class="white--text align-end"
                  height="400px"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                >
                  <v-avatar
                    class="profile"
                    color="grey"
                    size="164"
                    tile
                  >
                    <v-img src="https://cdn.vuetifyjs.com/images/profiles/marcus.jpg"></v-img>
                  </v-avatar>
                </v-img>
                <v-card-title>Top 10 Australian beaches{{cardNumber}}</v-card-title>

                <v-card-subtitle class="pb-0 mb-5">Number 10</v-card-subtitle>

                <v-card-text class="text--primary">
                  <div>Whitehaven Beach</div>

                  <div>Whitsunday Island, Whitsunday Islands</div>
                </v-card-text>
              </div>
              <v-card-actions class="d-flex justify-end">
                <v-btn
                    v-for="icon in icons"
                    :key="icon"
                    class="mx-4"
                    icon
                >
                  <v-icon size="24px">{{ icon }}</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
        </div>
    </v-container>
  </div>
</template>

<script>
export default {
  data () {
    return {
      cardNumbers: [
        1, 2, 3
      ],
      icons: [
        'fas fa-cut',
        'fas fa-share-alt',
        'far fa-times-circle'
      ]
    }
  },
  methods: {
    goDetail () {
      this.$router.push('/ArticleDetail')
    }
  }
}
</script>

<style>
</style>
