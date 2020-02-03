<template>
  <v-content>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar dark color="teal darken-1">
            <v-toolbar-title >개인 정보 관리 </v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
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

              <v-text-field
                v-model="company"
                :counter="10"
                label="Company"
                data-vv-name="company"
                required
                > </v-text-field>

              <V-file-input multiple label="Profile Image" v-model="image"></V-file-input>

              <v-btn @click="addMem" color="blue">submit</v-btn>
              <v-btn @click="clear" color="grey lighten-3">clear</v-btn>
            </form>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
  </v-content>
</template>

<script>
import http from '../http-common'
import upload from '../http-fileupload'

export default {
  name: 'personaldatamodify',
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
      imgae: null,
      countries: ['Afghanistan', 'Albania', 'Algeria', 'Andorra', 'Angola', 'Anguilla', 'Antigua &amp; Barbuda', 'Argentina', 'Armenia', 'Aruba', 'Australia', 'Austria', 'Azerbaijan', 'Bahamas', 'Bahrain', 'Bangladesh', 'Barbados', 'Belarus', 'Belgium', 'Belize', 'Benin', 'Bermuda', 'Bhutan', 'Bolivia', 'Bosnia &amp; Herzegovina', 'Botswana', 'Brazil', 'British Virgin Islands', 'Brunei', 'Bulgaria', 'Burkina Faso', 'Burundi', 'Cambodia', 'Cameroon', 'Cape Verde', 'Cayman Islands', 'Chad', 'Chile', 'China', 'Colombia', 'Congo', 'Cook Islands', 'Costa Rica', 'Cote D Ivoire', 'Croatia', 'Cruise Ship', 'Cuba', 'Cyprus', 'Czech Republic', 'Denmark', 'Djibouti', 'Dominica', 'Dominican Republic', 'Ecuador', 'Egypt', 'El Salvador', 'Equatorial Guinea', 'Estonia', 'Ethiopia', 'Falkland Islands', 'Faroe Islands', 'Fiji', 'Finland', 'France', 'French Polynesia', 'French West Indies', 'Gabon', 'Gambia', 'Georgia', 'Germany', 'Ghana', 'Gibraltar', 'Greece', 'Greenland', 'Grenada', 'Guam', 'Guatemala', 'Guernsey', 'Guinea', 'Guinea Bissau', 'Guyana', 'Haiti', 'Honduras', 'Hong Kong', 'Hungary', 'Iceland', 'India', 'Indonesia', 'Iran', 'Iraq', 'Ireland', 'Isle of Man', 'Israel', 'Italy', 'Jamaica', 'Japan', 'Jersey', 'Jordan', 'Kazakhstan', 'Kenya', 'Kuwait', 'Kyrgyz Republic', 'Laos', 'Latvia', 'Lebanon', 'Lesotho', 'Liberia', 'Libya', 'Liechtenstein', 'Lithuania', 'Luxembourg', 'Macau', 'Macedonia', 'Madagascar', 'Malawi', 'Malaysia', 'Maldives', 'Mali', 'Malta', 'Mauritania', 'Mauritius', 'Mexico', 'Moldova', 'Monaco', 'Mongolia', 'Montenegro', 'Montserrat', 'Morocco', 'Mozambique', 'Namibia', 'Nepal', 'Netherlands', 'Netherlands Antilles', 'New Caledonia', 'New Zealand', 'Nicaragua', 'Niger', 'Nigeria', 'Norway', 'Oman', 'Pakistan', 'Palestine', 'Panama', 'Papua New Guinea', 'Paraguay', 'Peru', 'Philippines', 'Poland', 'Portugal', 'Puerto Rico', 'Qatar', 'Reunion', 'Romania', 'Russia', 'Rwanda', 'Saint Pierre &amp; Miquelon', 'Samoa', 'San Marino', 'Satellite', 'Saudi Arabia', 'Senegal', 'Serbia', 'Seychelles', 'Sierra Leone', 'Singapore', 'Slovakia', 'Slovenia', 'South Africa', 'South Korea', 'Spain', 'Sri Lanka', 'St Kitts &amp; Nevis', 'St Lucia', 'St Vincent', 'St. Lucia', 'Sudan', 'Suriname', 'Swaziland', 'Sweden', 'Switzerland', 'Syria', 'Taiwan', 'Tajikistan', 'Tanzania', 'Thailand', "Timor L'Este", 'Togo', 'Tonga', 'Trinidad &amp; Tobago', 'Tunisia', 'Turkey', 'Turkmenistan', 'Turks &amp; Caicos', 'Uganda', 'Ukraine', 'United Arab Emirates', 'United Kingdom', 'United States', 'Uruguay', 'Uzbekistan', 'Venezuela', 'Vietnam', 'Virgin Islands (US)', 'Yemen', 'Zambia', 'Zimbabwe'],
      errorMessages: '',
      address: null,
      submit: '',
      clear: '',
      fulladdress: ''
      // country: ''
    }
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
    }
  },

  methods: {
    addMem () {
      let fdata = new FormData()
      let bday = this.birthday.replace('-', '')
      fdata.append('memberid', this.memberid)
      fdata.append('email', this.email)
      fdata.append('pw', this.pw)
      fdata.append('lastname', this.lastname)
      fdata.append('firstname', this.firstname)
      // fdata.append'(//',menu2: this.menu2)
      fdata.append('birthday', bday)
      fdata.append('gender', this.gender)
      fdata.append('area', this.area)
      fdata.append('job', this.job)
      fdata.append('company', this.company)
      fdata.append('image', this.image)
      http.post('/member/register', fdata)
        .then((response) => {
          console.log(response)
        })
        .catch((msg) => {
          console.log(msg)
        })
      this.profileUpload()
    },
    profileUpload () {
      const image = new FormData()
      image.append('file', this.imgae)
      upload
        .post('/upload', image)
        .then(res => {
          if (res.data.isupload === true) {
            console.log('success upload')
          }
        })
        .catch(() => {
          console.log('error occurd while upload image')
        })
    }
  }
}
</script>

<style>
</style>
