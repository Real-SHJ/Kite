<template>
<v-content>
    <v-layout justify-center row wrap>
        <v-flex xs11 style="text-align: right;">
            <h1 class="display-2 font-weight-thin mb=3"> Friends Search </h1>
            <!-- <h4 class="subheading">Find what your missing</h4> -->
        </v-flex>

        <v-flex xs11>
            <v-layout align-center justify-end="" row wrap>
                <v-flex xs2>
                    <v-form >
                        <v-text-field outline label="Search for firends" append-icon="search">
                        </v-text-field>
                    </v-form>
                </v-flex>
            </v-layout>
        </v-flex>
    </v-layout>
     <v-row>
      <v-col cols="11" sm="2">
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
              v-model="date_search_start"
              label="검색 시작일 - Calendar Click"
              prepend-icon="event"
              readonly
              v-on="on"
            > </v-text-field>
          </template>
          <v-date-picker v-model="date_search_start"
            year-icon="mdi-calendar-blank"
            prev-icon="mdi-skip-previous"
            next-icon="mdi-skip-next"
            @input="menu2 = false"> </v-date-picker>
        </v-menu>
      <v-spacer></v-spacer>
      </v-col>~
      <v-col cols="11" sm="2">
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
              v-model="date_search_end"
              label="검색 마지막날 - Calendar Click"
              prepend-icon="event"
              readonly
              v-on="on"
            > </v-text-field>
          </template>
          <v-date-picker v-model="date_search_end"
            year-icon="mdi-calendar-blank"
            prev-icon="mdi-skip-previous"
            next-icon="mdi-skip-next"
            @input="menu2 = false"> </v-date-picker>
        </v-menu>
      <v-spacer></v-spacer>
      </v-col>
     </v-row>
</v-content>
</template>

<script>
export default {
}
</script>

<style>

</style>
