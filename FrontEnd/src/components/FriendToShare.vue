<template>
  <v-card
    class="mx-3 my-2"
    @click="articleShare"
  >
    <v-list-item three-line>
      <v-list-item-avatar>
        <img
          :src="`http://13.125.153.118:8999/img/profile/${friend.image}`"
          alt=""
        >
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title class="headline mb-1">
          {{ friend.lastname }}{{ friend.firstname }}
        </v-list-item-title>
        <v-list-item-subtitle>{{ friend.email }}</v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-avatar>
        <v-btn
          outlined
          large
          fab
          :style="`color: ${textColor}`"
        >
          <v-icon :style="`color: ${textColor}`">
            fas fa-check
          </v-icon>
        </v-btn>
      </v-list-item-avatar>
    </v-list-item>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Friendtoshare',
  props: {
    friend: Object
  },
  data () {
    return {
      textColor: 'grey',
      check: true
    }
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
  },
  computed: {
    ...mapGetters(['colorControl'])
  },
  watch: {
    colorControl: {
      handler () {
        this.check = true
        this.textColor = 'grey'
      }
    }
  }
}
</script>

<style>
.my-friend-name {
  font-size: 10px;
}
</style>
