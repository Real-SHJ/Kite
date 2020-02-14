<template>
  <v-content>
      <v-card @click="articleShare">
          <v-row>
              <v-col
                class="d-flex align-center"
                cols="5"
              >
                <span>내 친구 이름</span>
              </v-col>
              <v-col class="d-flex flex-row-reverse">
                <v-btn outlined large fab :style="`color: ${textColor}`"><v-icon :style="`color: ${textColor}`">fas fa-check</v-icon></v-btn>
              </v-col>
          </v-row>
      </v-card>
  </v-content>
</template>

<script>
export default {
  name: 'friendsToShare',
  data () {
    return {
      textColor: 'grey',
      check: true
    }
  },
  props: {
    friend: Object
  },
  methods: {
    articleShare () {
      if (this.check) {
        this.$emit('shareReq', this.friend.memberid)
        this.textColor = 'green'
      } else {
        this.$emit('cancelReq', this.friend.memberid)
        this.textColor = 'grey'
      }
      this.check = !this.check
    }
  }
}
</script>

<style>

</style>
