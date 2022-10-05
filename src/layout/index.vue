<template>
  <v-app>
    <v-app-bar color="purple" app>
      <template v-slot:prepend>
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      </template>
      <top-bar/>
    </v-app-bar>
    <v-navigation-drawer v-model="drawer" permanent elevation="6" app>
      <sidebar/>
    </v-navigation-drawer>
    <v-main>
      <v-container fluid>
        <router-view/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">

import Sidebar from "./component/Sidebar.vue";
import TopBar from "./component/TopBar.vue";
import {reactive, toRefs} from "vue";
const state = reactive({
  drawer: true,
})
const { drawer } = toRefs(state)

</script>

<style lang="scss" scoped>

</style>