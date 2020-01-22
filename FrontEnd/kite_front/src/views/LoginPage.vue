<template>
  <div class="loginPage">
    <h1>여기는 로그인 페이지입니다.</h1>
    <login-form></login-form>
  </div>
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
