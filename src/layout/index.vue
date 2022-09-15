<template>
  <v-app>
    <v-navigation-drawer elevation="6" app>
      <sidebar/>
    </v-navigation-drawer>
    <v-app-bar app>
      <v-toolbar-title>
        Logo
      </v-toolbar-title>
      <top-bar/>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <app-main/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">

import Sidebar from "./component/Sidebar.vue";
import TopBar from "./component/TopBar.vue";
import AppMain from "./component/AppMain.vue";
</script>

<style scoped>

</style>