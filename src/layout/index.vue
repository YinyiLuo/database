<template>
  <v-app>
    <v-navigation-drawer elevation="6" app>
      <sidebar/>
    </v-navigation-drawer>
    <v-app-bar app>
      <v-toolbar-title>
        <div class="search">
          <div style="width: 50%">
            <v-text-field  append-inner-icon="mdi-send" @click:append-inner="searchClick"/>

          </div>
        </div>
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
function searchClick() {
  console.log("hello")
}
</script>

<style lang="scss" scoped>
.search {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
}
</style>