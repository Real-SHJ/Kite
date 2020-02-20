<template>
  <div id="app">
    <KakaoLogin
      api-key="a1b690cf634876a3bd96471c5106bd7f"
      image="kakao_login_btn"
      :on-success="onSuccess"
      :on-failure="onFailure"
    />
  </div>
</template>

<script>
import http from '../http-common'
import KakaoLogin from 'vue-kakao-login'

const onSuccess = (data) => {
  console.log(data)
  console.log('success')
  console.log(JSON.stringify(data))
  const dform = new FormData()
  dform.append('data', data)
  http
    .post('/test/kakao', dform)
    .then(res => {
      console.log('성공적으로 보냈습니다!!')
    })
}
const onFailure = (data) => {
  console.log(data)
  console.log('failure')
}

export default {
  name: 'App',
  components: {
    KakaoLogin
  },
  data: function () {
    return { message: 'Hello!' }
  },

  methods: {
    onSuccess,
    onFailure
  }
}
</script>

<style>
</style>
