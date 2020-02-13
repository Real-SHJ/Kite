<template>
  <v-content>
    <h1> 개인 저장소야 기범아 </h1>
    <v-btn @click="test">test</v-btn>
  </v-content>
</template>

<script>
export default {
  data () {
    return {
      count: 0
    }
  },
  name: 'indirepository',
  methods: {
    test () {
      console.log('클릭!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
      this.count++
      console.log(this.count)
    }
  },
  mounted () {
    console.log('기범아 왜 안 찍히냐고')
  }
}
</script>

<style>

</style>
