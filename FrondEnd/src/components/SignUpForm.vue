<template>
    <div class="signup-form-page">
        <div class="login-div col-6 offset-3">
            <div v-if="errors.length" class="error-list alert alert-danger">
                <div v-for="(error, idx) in errors" :key="idx">{{error}}</div>
            </div>
            <div class="form-group">
                <label for="id">ID</label>
                <input id="id" class="form-control" type="text" v-model="credential.username">
            </div>
            <div class="form-grou">
                <label for="password">PASSWORD</label>
                <input id="password" class="form-control" type="password" v-model="credential.password">
            </div>
            <div class="form-grou">
                <label for="password-confirm">PASSWORD</label>
                <input id="password-confirm" name="password_confirmation" class="form-control" type="password" v-model="credential.passwordConfirm">
            </div>
            <button class="btn btn-primary" @click="SignUp">Submit</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import router from '../router'
export default {
  name: 'loginform',
  data () {
    return {
      credential: {
        username: '',
        password: '',
        passwordConfirm: ''
      },
      errors: []
    }
  },

  methods: {
    SignUp () {
      if (this.checkForm()) {
        axios.post('http://localhost:8080/SignUp', { username: this.credential.username, password: this.credential.password })
          .then((res) => {
            console.log(res)
            router.push('/')
          })
          .catch((e) => {
            console.e(e)
          })
      }
    },
    checkForm () {
      this.errors = []
      if (this.credential.password.length < 8) {
        this.errors.push('비밀번호는 8 글자가 넘어야 합니다. ')
      }
      if (this.credential.password.length !== this.credential.passwordConfirm) {
        this.errors.push('비밀번호가 같지 않습니다.')
      }
      if (!this.credential.username) {
        this.error.push('아이디를 입력해주세요.')
      }
    }
  }
}
</script>
