<template>
  <v-container>
    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >

      <v-text-field
        v-model="credential.email"
        label="E-mail"
        :rules="credential.emailRules"
        required
      ></v-text-field>

      <v-text-field
        v-model="credential.pw"
        label="password"
        type="password"
        :rules="credential.pwRules"
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
// import router from '../router'
export default {
  data () {
    return {
      valid: true,
      credential: {
        pw: '',
        pwRules: [
          (v) => !!v || 'password is required',
          (v) => (v && v.length <= 10) || 'password must be less than 10 characters'
        ],
        email: '',
        emailRules: [
          (v) => !!v || 'E-mail is required',
          (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid'
        ]
      }
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
        const fdata = new FormData()
        fdata.append('email', this.credential.email)
        fdata.append('pw', this.credential.pw)
        http.post('/member/signin', fdata)
          .then(res => {
            // 토큰 저장
            const { token } = res.data.access_token
            this.$session.set('my-session-token', token)
            this.$store.dispatch('login', token)
            console.log('로그인 성공!!')
            // router.push('/')
          })
          .catch(err => console.log(err))
      }
      const fdata2 = new FormData()
      fdata2.append('email', this.credential.email)
      http.post('/member/info', fdata2)
        .then(res2 => {
          console.log(res2.data)
          // 리다이렉트
        })
    }
  }
}
</script>

<style>

</style>
