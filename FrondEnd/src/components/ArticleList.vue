<template>
  <div>
    <v-content>
      <div v-for="cardNumber in cardNumbers" :key="cardNumber.id" class="my-3">
        <v-card
          class="mx-auto my-auto"
          max-width="400"
        >
          <div @click="goDetail">
            <v-img
              class="white--text align-end"
              height="200px"
              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
            >
              <v-card-title>Top 10 Australian beaches{{cardNumber}}</v-card-title>
            </v-img>

            <v-card-subtitle class="pb-0">Number 10</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitehaven Beach</div>

              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>
          </div>
          <v-card-actions class="d-flex justify-center">
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
    </v-content>
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
