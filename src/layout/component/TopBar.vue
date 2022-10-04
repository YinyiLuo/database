<template>
  <v-menu>
    <template #activator="{ props }">
      <v-btn icon="mdi-cog" v-bind="props"/>
    </template>
    <v-list>
      <v-list-item
          v-for="(item, index) in items"
          :key="index" @click="item.onClick()">
        <v-list-item-title>{{ item.title }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script lang="ts" setup>

import useUserStore from "../../store/modules/user";

const userStore = useUserStore()

const items = [
  {
    title: 'Logout',
    onClick() {
      userStore.logout().then(() => {
        location.href = '/index'
      }).catch(() => {})
    }
  }
]

</script>

<style scoped>

</style>