<template>
  <div id="app">
    <NaverLogin
      client-id="UmIfLWnhqmTUkrkKC3k0"
      callback-url="http://13.125.153.118:8085/api/member/naverlogin"
      is-popup="false"
      :callback-function="callbackFunction"
    />
  </div>
</template>

<script>
import NaverLogin from 'vue-naver-login'

const callbackFunction = (status) => {
  if (status) {
    /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
    var email = NaverLogin.user.getEmail()
    if (email === undefined || email == null) {
      alert('이메일은 필수정보입니다. 정보제공을 동의해주세요.')
      /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
      NaverLogin.reprompt()
      return
    }

    window.location.replace('http://' + window.location.hostname + ((location.port === '' || location.port === undefined) ? '' : ':' + location.port) + '/sample/main.html')
  } else {
    console.log('callback 처리에 실패하였습니다.')
  }
}

export default {
  name: 'App',
  components: {
    NaverLogin
  },
  methods: {
    callbackFunction
  }
}
</script>
