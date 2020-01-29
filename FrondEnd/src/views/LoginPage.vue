<template>
  <v-content>
    <h1>여기는 로그인 페이지입니다.</h1>
    <LoginForm/>
  </v-content>
</template>

<script>
import LoginForm from '../components/LoginForm.vue'
export default {
  name: 'login',

  components: {
    LoginForm
  }
}
</script>

<style>

</style>
