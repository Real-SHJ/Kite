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
        @click="validate"
      >
        로그인
      </v-btn>
    </v-form>
  </v-container>
</template>

<script>
export default {
  data: () => ({
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
  }),

  methods: {
    validate () {
      if (this.$refs.form.validate()) {
        this.snackbar = true
      }
    }
  }
}
</script>

<style>

</style>
