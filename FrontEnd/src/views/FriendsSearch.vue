<template>
    <v-content>
        <v-parallax height="800" drak src="https://image.fmkorea.com/files/attach/new/20190327/559683442/658855287/1696026319/42976da43ad326f60e7b2e5fb4f39dd8.jpg">
            <v-container fluid grid-list-xl>

                <v-layout justify-center row wrap>
                    <v-flex xs12 style="text-align: center;">
                        <h1 class="display-3 font-weight-thin mb=3"> Friends Search </h1>
                        <h4 class="subheading">Find what your missing</h4>
                    </v-flex>

                    <v-flex xs12>
                        <v-layout align-center justify-center row wrap>
                            <v-flex xs6>
                                <v-form>
                                    <v-text-field outline label="Search for firends" append-icon="search">
                                    </v-text-field>
                                </v-form>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>

            </v-container>
        </v-parallax>
        <v-card class="d-inline-block mx-auto">
            <v-container>
                <v-row justify="space-between">
                    <v-col cols="auto">
                        <v-img
                            height="200"
                            width="200"
                            src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
                            > </v-img>
                    </v-col>

                    <v-col
                        cols="auto"
                        class="text-center pl-0"
                        >
                        <v-row
                            class="flex-column ma-0 fill-height"
                            justify="center"
                            >
                            <v-col class="px-0">
                                <v-btn icon>
                                    <v-icon>mdi-heart</v-icon>
                                </v-btn>
                            </v-col>

                            <v-col class="px-0">
                                <v-btn icon>
                                    <v-icon>mdi-bookmark</v-icon>
                                </v-btn>
                            </v-col>

                            <v-col class="px-0">
                                <v-btn icon>
                                    <v-icon>mdi-share-variant</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-card>
    </v-content>
</template>

<script>
export default {

}
</script>

<style>

</style>
