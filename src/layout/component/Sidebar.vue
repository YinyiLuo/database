<template>
  <v-list nav>
    <v-list-item v-for="item in items" prepend-icon="mdi-cursor-default"
                 :key="item.title" title="item.title" :to="item.to">

    </v-list-item>
  </v-list>
</template>

<script lang="ts" setup>
import {reactive, toRefs} from "vue";

const state = reactive({
  items: [
    {
      title: '首页',
      to: '/'
    },
    {
      title: '我的音乐',
      to: '/'
    },
    {
      title: '搜索',
      to: '/'
    },
    {
      title: '浏览记录',
      to: '/'
    },
    {
      title: '上传',
      to: '/'
    }
  ]
})
const { items } = toRefs(state)
</script>

<style scoped>

</style>