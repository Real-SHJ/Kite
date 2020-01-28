<template>
  <v-app id="app">
    <KakaoLogin
      api-key="a1b690cf634876a3bd96471c5106bd7f"
      image="kakao_login_btn_large"
      :on-success=onSuccess
      :on-failure=onFailure
      />
      <input v-model="message">
      <p>{{message}}</p>
  </v-app>
</template>

<script>

import KakaoLogin from 'vue-kakao-login'

const onSuccess = (data) => {
  console.log(data)
  console.log('success')
  alert(JSON.stringify(data))
}
const onFailure = (data) => {
  console.log(data)
  console.log('failure')
}

export default {
  name: 'App',
  data: function () {
    return { message: 'Hello!' }
  },
  components: {
    KakaoLogin
  },

  methods: {
    onSuccess,
    onFailure
  }
}
</script>

<style>
</style>
