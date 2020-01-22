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
                v-model="name"
                :counter="10"
                label="Name"
                data-vv-name="name"
                required
                > </v-text-field>
              <v-text-field
                v-model="email"
                :counter="10"
                label="E-mail"
                data-vv-name="email"
                required
                > </v-text-field>
              <v-select
                :items="items"
                v-model="select"
                label="Select"
                data-vv-name="select"
                required
                > </v-select>
              <v-checkbox
                v-model="checkbox"
                vaule="1"
                label="Option"
                data-vv-name="checkbox"
                type="checkbox"
                required
                > </v-checkbox>

              <v-btn @click="submit" color="blue">submit</v-btn>
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
export default {

}
</script>

<style>

</style>
