<template>
  <v-content>
    <h1>여기는 메인 페이지입니다...</h1>
    <ArticleList/>
    <InfiniteLoading></InfiniteLoading>
  </v-content>
</template>

<script>
import ArticleList from '../components/ArticleList.vue'
import InfiniteLoading from 'vue-infinite-loading'
export default {
  components: {
    ArticleList,
    InfiniteLoading
  },
  methods: {

  }
}
</script>

<style>

</style>
