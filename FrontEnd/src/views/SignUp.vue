<template>
  <v-content>
  <v-form>
    <v-container>
      <v-row align="center">
        <v-col cols="12" sm="6">
          <v-form ref="form" >
            <v-text-field v-model="name" :counter="10" :rules="nameRules" label="Name" required></v-text-field>
            <v-text-field v-model="email" :rules="nameRules" label="E-mail" required></v-text-field>
            <v-text-field v-model="password" label="Password" required> </v-text-field>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
    <v-container fluid>
      <v-row>
        <v-col cols="12" sm="6">
          <v-text-field v-model="password1"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show1 ? 'text' : 'password'"
                  name="input-10-1"
                  label="Normal with hint text"
                  hint="At least 8 characters"
                  counter
                  @click:append="show1 = !show1"
          > </v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
          <v-text-field v-model="password2"
                  :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show2 ? 'text' : 'password'"
                  name="input-10-2"
                  label="Visible"
                  hint="At least 8 characters"
                  class="input-group-focused"
                  @click:append="show2 = !show2"
          > </v-text-field>
          </v-col>
          <!-- <v-col cols="12" sm="6">
          <v-text-field
                  :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show3 ? 'text' : 'password'"
                  name="input-10-2"
                  label="Not visible"
                  hint="At least 8 characters"
                  class="input-group-focused"
                  @click:append="show3 = !show3"
          > </v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
          <v-text-field
                  :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.emailMatch]"
                  :type="show4 ? 'text' : 'password'"
                  name="input-10-2"
                  label="Error"
                  hint="At least 8 characters"
                  error
                  @click:append="show4 = !show4"
          > </v-text-field>
          </v-col> -->
      </v-row>
    </v-container>
    <div>
      <v-btn color="success">Success</v-btn>
      <v-btn color="error">error</v-btn>
      <v-btn color="warning">Warning</v-btn>
      <v-btn color="info">Info</v-btn>
      <v-btn @click="submit" color="blue">submit</v-btn>
      <v-btn @click="clear" color="grey lighten-3">clear</v-btn>
    </div>
  </v-form>
  </v-content>
</template>

<script>
export default {
  data () {
    return {
      name: '',
      // password1: '',
      // password2: '',
      show1: false,
      show2: true,
      show3: false,
      show4: false,
      // password: 'Password',
      password1: '',
      password2: '',
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
        // pwdmatch: this.password1 == this.password2,
        emailMatch: () => ('The eail and password you entered don\'t match')
      }
    }
  }
}
</script>

<style>
</style>
