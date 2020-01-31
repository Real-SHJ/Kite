<template>
  <v-container>
    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >

      <v-text-field
        v-model="email"
        label="E-mail"
        :rules="emailRules"
        required
      ></v-text-field>

      <v-text-field
        v-model="password"
        :counter="20"
        label="Password"
        :rules="passwordRules"
        required
      ></v-text-field>

      <v-btn
        color="primary"
        class="mr-4"
        @click="login"
      >
        로그인
      </v-btn>
    </v-form>
  </v-container>
</template>

<script>
import http from '../http-common'
import router from '../router'
export default {
  data () {
    return {
      valid: true,
      password: '',
      passwordRules: [
        (v) => !!v || 'Name is required',
        (v) => (v && v.length <= 10) || 'Name must be less than 10 characters'
      ],
      email: '',
      emailRules: [
        (v) => !!v || 'E-mail is required',
        (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid'
      ]
    }
  },

  methods: {
    validate () {
      if (this.$refs.form.validate()) {
        this.snackbar = true
      }
    },

    login () {
      if (this.valid) {
        http.post('/login', { email: this.email, password: this.password })
          .then(res => {
            // 토큰 저장
            const { token } = res.data
            this.$store.dispatch('login', token)
            // 리다이렉트
            router.push('/')
          })
          .catch(err => console.log(err))
      }
    }
  }
}
</script>

<style>

</style>
