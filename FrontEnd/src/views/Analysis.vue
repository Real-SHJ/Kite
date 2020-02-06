<template>
    <v-tabs>
        <v-tab><router-link to="/wordcloud" replace>키워드</router-link></v-tab>
        <v-tab><router-link to="/forcedirected" replace>연관 관계 그래프</router-link></v-tab>
    </v-tabs>
</template>
<script>
export default {
  name: 'analysis'

}
</script>

<style scoped>
</style>
