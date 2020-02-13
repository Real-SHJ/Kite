<template>
  <v-app>
      <v-content>
          <v-container class="fill-height" fluid>
              <v-row align="center" justify="center">
                  <v-col cols="12" sm="8" md="8">
                      <v-card class="elevation-12">
                          <v-window v-model="step">
                            <v-window-item :value="1">
                                <v-row>
                                    <v-col cols="12" md="8">
                                        <v-card-text class="mt-12">
                                            <h1 class="text-center display-2 teal--text text--accent-3">Sign in to Kite</h1>
                                            <div class="text-center" mt-4>
                                                <v-btn class="mx-2" fab color="black" outlined="">
                                                    <v-icon>fab fa-facebook-f</v-icon>
                                                </v-btn>
                                                <v-btn class="mx-2" fab color="black" outlined="">
                                                    <v-icon>fab fa-google-g</v-icon>
                                                </v-btn>
                                                <v-btn class="mx-2" fab color="black" outlined="">
                                                    <v-icon>fab fa-linkedin-in</v-icon>
                                                </v-btn>
                                            </div>
                                            <h4 class="text-center mlt-4">Ensure your email for registration</h4>
                                            <v-form>
                                                <v-text-field
                                                    label="Email"
                                                    name="Email"
                                                    prepend-icon="email"
                                                    type="text"
                                                    v-model="credential.email"
                                                    :rules="credential.emailRules"
                                                    color="teal accent-3"
                                                    />
                                                <v-text-field
                                                    id="password"
                                                    label="Password"
                                                    name="Password"
                                                    v-model="credential.pw"
                                                    :rules="credential.pwRules"
                                                    prepend-icon="lock"
                                                    type="password"
                                                    color="teal accent-3"
                                                />

                                            </v-form>
                                            <h3 class="text-center mt-3">Forget your password ?</h3>
                                            </v-card-text>
                                                <div class="text-center mt-3">
                                                    <v-btn @click="login" rounded color="tea1 accent-3" dark>SIGN IN </v-btn>
                                                </div>
                                    </v-col>
                                    <v-col cols="12" md="4" class="teal accent-3">
                                        <v-card-text class="white--text mt-12">
                                            <h1 class="text-center display-1">Hello, Friends !</h1>
                                            <h5 class="text-center">Enter your personel details and start journay with us </h5>
                                        </v-card-text>
                                        <div class="text-center">
                                            <v-btn rounded outlined="" dark @click="step++">SIGN UP</v-btn>
                                        </div>
                                    </v-col>
                                </v-row>
                            </v-window-item>
                            <v-window-item :value="2">
                                <v-row class="fill-height">
                                    <v-col cols="12" md="4" class="teal accent-3">
                                        <v-card-text class="white--text mt-12">
                                            <h1 class="text-center display-1">Welcome Back !!! </h1>
                                            <h5 class="text-center">To Keep connected with us please login with your personnel info </h5>
                                        </v-card-text>
                                        <div class="text-center">
                                            <v-btn rounded outlined dark @click="step--">SIGN IN</v-btn>
                                        </div>
                                    </v-col>
                                    <v-col cols="12" md="8">
                                        <v-card-text>
            <form>
              <v-text-field
                v-model="email"
                :counter="20"
                label="E-mail"
                data-vv-name="email"
                required
                > </v-text-field>
              <v-text-field
                v-model="pw"
                :counter="10"
                label="Pw"
                data-vv-name="pw"
                required
                > </v-text-field>
                <v-text-field
                v-model="lastname"
                :counter="6"
                label="LastName"
                data-vv-name="lastname"
                required
                > </v-text-field>
                <v-text-field
                v-model="firstname"
                :counter="6"
                label="FirstName"
                data-vv-name="firstname"
                required
                > </v-text-field>
                  <v-menu
                    v-model="menu2"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    trasition="scale-transition"
                    offset-y
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        v-model="birthday"
                        label="생년월일 - Calendar Click"
                        prepend-icon="event"
                        readonly
                        v-on="on"
                      > </v-text-field>
                    </template>
                    <v-date-picker v-model="birthday"
                      year-icon="mdi-calendar-blank"
                      prev-icon="mdi-skip-previous"
                      next-icon="mdi-skip-next"
                     @input="menu2 = false"> </v-date-picker>
                  </v-menu>
                <v-spacer></v-spacer>

              <v-radio-group v-model="gender" row>
                <v-radio
                  label="남자" value="남자"
                  > </v-radio>
                  <v-radio
                  label="여자" value="여자"
                  > </v-radio>
              </v-radio-group>
              <p>{{gender}}</p>
              <v-autocomplete
                ref="area"
                v-model="area"
                :rules="[() => !!area || 'This field is required']"
                :items="countries"
                label="area"
                placeholder="Select..."
                required
              > </v-autocomplete>

              <v-text-field
                v-model="job"
                :counter="10"
                label="job"
                data-vv-name="job"
                required
                > </v-text-field>

              <V-file-input multiple label="Profile Image" v-model="image"></V-file-input>
            <div class="text-center mt-3">
              <v-btn @click="addMem"  color="blue">submit</v-btn>
              <v-btn @click="clear" color="grey lighten-3">clear</v-btn>
            </div>
            </form>
          </v-card-text>
                                    </v-col>
                                </v-row>
                            </v-window-item>
                          </v-window>
                      </v-card>
                  </v-col>
              </v-row>
          </v-container>
      </v-content>
  </v-app>
</template>

<script>
import http from '../http-common'
import upload from '../http-fileupload'
import router from '../router'

export default {
  name: 'SignUp',
  data () {
    return {
      memberid: 0,
      email: '',
      pw: '',
      lastname: '',
      firstname: '',
      menu2: false,
      birthday: new Date().toISOString().substr(0, 10),
      // date: new Date().toISOString().substr(0, 10),
      gender: '',
      area: '',
      job: '',
      company: '',
      image: null,
      countries: ['Afghanistan', 'Albania', 'Algeria', 'Andorra', 'Angola', 'Anguilla', 'Antigua &amp; Barbuda', 'Argentina', 'Armenia', 'Aruba', 'Australia', 'Austria', 'Azerbaijan', 'Bahamas', 'Bahrain', 'Bangladesh', 'Barbados', 'Belarus', 'Belgium', 'Belize', 'Benin', 'Bermuda', 'Bhutan', 'Bolivia', 'Bosnia &amp; Herzegovina', 'Botswana', 'Brazil', 'British Virgin Islands', 'Brunei', 'Bulgaria', 'Burkina Faso', 'Burundi', 'Cambodia', 'Cameroon', 'Cape Verde', 'Cayman Islands', 'Chad', 'Chile', 'China', 'Colombia', 'Congo', 'Cook Islands', 'Costa Rica', 'Cote D Ivoire', 'Croatia', 'Cruise Ship', 'Cuba', 'Cyprus', 'Czech Republic', 'Denmark', 'Djibouti', 'Dominica', 'Dominican Republic', 'Ecuador', 'Egypt', 'El Salvador', 'Equatorial Guinea', 'Estonia', 'Ethiopia', 'Falkland Islands', 'Faroe Islands', 'Fiji', 'Finland', 'France', 'French Polynesia', 'French West Indies', 'Gabon', 'Gambia', 'Georgia', 'Germany', 'Ghana', 'Gibraltar', 'Greece', 'Greenland', 'Grenada', 'Guam', 'Guatemala', 'Guernsey', 'Guinea', 'Guinea Bissau', 'Guyana', 'Haiti', 'Honduras', 'Hong Kong', 'Hungary', 'Iceland', 'India', 'Indonesia', 'Iran', 'Iraq', 'Ireland', 'Isle of Man', 'Israel', 'Italy', 'Jamaica', 'Japan', 'Jersey', 'Jordan', 'Kazakhstan', 'Kenya', 'Kuwait', 'Kyrgyz Republic', 'Laos', 'Latvia', 'Lebanon', 'Lesotho', 'Liberia', 'Libya', 'Liechtenstein', 'Lithuania', 'Luxembourg', 'Macau', 'Macedonia', 'Madagascar', 'Malawi', 'Malaysia', 'Maldives', 'Mali', 'Malta', 'Mauritania', 'Mauritius', 'Mexico', 'Moldova', 'Monaco', 'Mongolia', 'Montenegro', 'Montserrat', 'Morocco', 'Mozambique', 'Namibia', 'Nepal', 'Netherlands', 'Netherlands Antilles', 'New Caledonia', 'New Zealand', 'Nicaragua', 'Niger', 'Nigeria', 'Norway', 'Oman', 'Pakistan', 'Palestine', 'Panama', 'Papua New Guinea', 'Paraguay', 'Peru', 'Philippines', 'Poland', 'Portugal', 'Puerto Rico', 'Qatar', 'Reunion', 'Romania', 'Russia', 'Rwanda', 'Saint Pierre &amp; Miquelon', 'Samoa', 'San Marino', 'Satellite', 'Saudi Arabia', 'Senegal', 'Serbia', 'Seychelles', 'Sierra Leone', 'Singapore', 'Slovakia', 'Slovenia', 'South Africa', 'South Korea', 'Spain', 'Sri Lanka', 'St Kitts &amp; Nevis', 'St Lucia', 'St Vincent', 'St. Lucia', 'Sudan', 'Suriname', 'Swaziland', 'Sweden', 'Switzerland', 'Syria', 'Taiwan', 'Tajikistan', 'Tanzania', 'Thailand', "Timor L'Este", 'Togo', 'Tonga', 'Trinidad &amp; Tobago', 'Tunisia', 'Turkey', 'Turkmenistan', 'Turks &amp; Caicos', 'Uganda', 'Ukraine', 'United Arab Emirates', 'United Kingdom', 'United States', 'Uruguay', 'Uzbekistan', 'Venezuela', 'Vietnam', 'Virgin Islands (US)', 'Yemen', 'Zambia', 'Zimbabwe'],
      errorMessages: '',
      address: null,
      submit: '',
      clear: '',
      fulladdress: '',
      step: 1,
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
  props: {
    source: String
  },
  conponents: {
    // DaumPostcode
  },

  computed: {
    form () {
      return {
        name: this.name,
        address: this.address,
        city: this.city,
        state: this.state,
        zip: this.zip,
        country: this.country
      }
    }
  },

  watch: {
    name () {
      this.errorMessages = ''
    },
    image: function () {
      console.log(this.image)
    }
  },

  methods: {
    addMem () {
      let fdata = new FormData()
      let bday = this.birthday.replace('-', '')
      let ext = 'temp.jpg'
      if (this.image === null) { ext = 'temg.jpg' } else { ext = this.email + '.' + this.image[0].name.split('.')[1] }
      this.step--
      fdata.append('memberid', this.memberid)
      fdata.append('email', this.email)
      fdata.append('pw', this.pw)
      fdata.append('lastname', this.lastname)
      fdata.append('firstname', this.firstname)
      fdata.append('birthday', bday)
      fdata.append('gender', this.gender)
      fdata.append('area', this.area)
      fdata.append('job', this.job)
      fdata.append('company', this.company)
      fdata.append('image', ext)
      http.post('/member/signup', fdata)
        .then((response) => {
          console.log(response)
        })
        .catch((msg) => {
          console.log(msg)
        })
        .finally(() => {
          this.profileUpload(this.image[0], ext)
        })
      console.log('프로필 전')
      // this.profileUpload()
      console.log('프로필 후후후')
    },
    profileUpload (profileImage, ext) {
      const image = new FormData()
      // profileImage.name = ext
      console.log(profileImage)
      console.log(profileImage.name)
      image.append('profile', profileImage)
      image.append('imageName', ext)
      console.log(image)
      upload
        .post('/profile/upload', image)
        .then(res => {
          if (res.data.isupload === true) {
            console.log('success upload')
            console.log(res)
          }
        })
        .catch(() => {
          console.log('error occurd while upload image')
        })
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
            console.log('token: ' + token)
            this.$session.set('my-token', token)
            this.$store.dispatch('login', token)
            let companylist = ''
            console.log('로그인 성공!!')
            http.post('/member/info', fdata)
              .then(res2 => {
                const userEmail = res2.data.result.email
                const userName = `${res2.data.result.lastname} ${res2.data.result.firstname}`
                const userid = res2.data.result.memberid
                const userImage = res2.data.result.image
                companylist = res2.data.result.company
                this.$store.dispatch('infoSave', { userEmail: userEmail, userName: userName, userid: userid, userImage: userImage, companylist: companylist })
                this.$session.set('my-info', { userEmail: userEmail, userName: userName, userid: userid, userImage: userImage, companylist: companylist })
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
