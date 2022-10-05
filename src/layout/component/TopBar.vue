<template>

  <v-app-bar-title>
    音乐播放器
  </v-app-bar-title>
  <div class="search">
    <div style="width: 50%; display: flex; justify-content: center; justify-items: center">
      <v-text-field density="compact"
                    append-inner-icon="mdi-magnify"
                    variant="outlined"
                    single-line
                    hide-details
                    label="搜索"
                    @click:append-inner="searchClick"/>
    </div>
  </div>
  <v-menu>
    <template #activator="{ props }">
      <v-btn icon v-bind="props">
        <v-avatar size="36px" class="mx-md-3">
          <v-img src="src/assets/avatar.jpg" :aspect-ratio="1" alt="avatar"/>
        </v-avatar>
      </v-btn>
    </template>
    <v-list width="200">
      <v-list-item-title class="mx-auto text-center">
        <v-avatar size="large" class="mx-md-3">
          <v-img src="src/assets/avatar.jpg" :aspect-ratio="1" alt="avatar"/>
        </v-avatar>
        <h4>{{avatarTitle}}</h4>
        <p class="text-caption mt-1">
          {{email}}
        </p>
      </v-list-item-title>
      <v-divider class="my-3"/>
      <v-list-item
          v-for="(item, index) in items" :prepend-icon="item.icon"
          :key="index" @click="item.onClick()">
        <v-list-item-title>{{ item.title }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script lang="ts" setup>

import useUserStore from "../../store/modules/user";
import myRouter from "../../router";
import {reactive, toRefs} from "vue";

const userStore = useUserStore()

const items = [
  {
    title: '账户设置',
    icon: 'mdi-cog',
    onClick() {
      myRouter.push({ path: '/settings' })
    }
  },
  {
    title: '退出',
    icon: 'mdi-logout',
    onClick() {
      userStore.logout().then(() => {
        location.href = '/index'
      }).catch(() => {})
    }
  },

]

const state = reactive({
  avatarTitle: '赵旻昆',
  email: '123456@789.com',

})
const { avatarTitle, email,  } = toRefs(state)

function searchClick() {

}
</script>

<style lang="scss" scoped>
.search {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 80%;
}
</style>