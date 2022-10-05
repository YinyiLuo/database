<template>
  <v-list nav rounded >
    <v-list-item color="purple" v-for="item in items" :prepend-icon="item.icon"
                 :key="item.title" :title="item.title" :to="item.to">

    </v-list-item>
  </v-list>
</template>

<script lang="ts" setup>
import {reactive, toRefs} from "vue";
const items = [
  {
    title: '首页',
    to: '/index',
    icon: 'mdi-home'
  },
  {
    title: '我的音乐',
    to: '/myMusic',
    icon: 'mdi-headphones'
  },
  {
    title: '搜索',
    to: '/search',
    icon: 'mdi-magnify'
  },
  {
    title: '浏览记录',
    to: '/record',
    icon: 'mdi-history'
  },
  {
    title: '上传',
    to: '/upload',
    icon: 'mdi-upload'
  }
]
const state = reactive({

})
const { } = toRefs(state)
</script>

<style scoped>

</style>