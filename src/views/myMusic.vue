<template>
  <v-container>
    <div class="text-h5">单曲</div>
    <v-row class="pa-3">
      <v-col v-for="card in [1, 2, 3]" :key="card" :cols="4">
        <v-card class="mx-auto" width="300">
          <v-img
              width="300"
              src="src/assets/material.jpg" :aspect-ratio="1" cover
          ></v-img>
          <v-card-title>音乐</v-card-title>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
  <v-divider class="mx-4"/>
  <v-container>
    <div class="text-h5">专辑</div>
  </v-container>
</template>

<script setup lang="ts">

</script>

<style scoped>

</style>