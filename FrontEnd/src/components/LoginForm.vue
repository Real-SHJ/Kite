<template>
  <v-card>
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
    <v-container class="d-inline-flex">
      <KakaoLogin class="mr-4"/>
      <NaverLogin/>
    </v-container>
  </v-card>
</template>

<script>
import http from '../http-common'
import router from '../router'
import KakaoLogin from './KakaoLogin'
import NaverLogin from './NaverLogin'

export default {
  components: {
    KakaoLogin,
    NaverLogin
  },
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
            const token = res.data.access_token
            console.log(token)
            this.$session.set('my-token', token)
            this.$store.dispatch('login', token)
            console.log('로그인 성공!!')
            http.post('/member/info', fdata)
              .then(res2 => {
                const userEmail = res2.data.result.email
                const userName = `${res2.data.result.lastname} ${res2.data.result.firstname}`
                const userid = res2.data.result.memberid
                const userImage = 'http://13.125.153.118:8999/img/profile/' + res2.data.result.image
                console.log(userName)
                console.log(res2.data.result)
                this.$store.dispatch('infoSave', { userEmail: userEmail, userName: userName, userid: userid, userImage: userImage })
                this.$session.set('my-info', { userEmail: userEmail, userName: userName, userid: userid, userImage: userImage })
              })
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
