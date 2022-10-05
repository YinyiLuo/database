<template>
  <v-container>
    <div class="pa-5">
      <div class="text-h4 ">搜索结果</div>
      <div class="text-caption mt-3">共查到{{totalResult}}条结果</div>
    </div>
    <v-table>
      <thead>
        <tr>
          <th style="padding-left: 35px" class=" text-subtitle-2 font-weight-light">歌曲名</th>
          <th style="padding-left: 35px" class="text-subtitle-2 font-weight-light">歌手</th>
          <th style="padding-left: 35px" class="text-subtitle-2 font-weight-light">专辑</th>
          <th class="text-subtitle-2 font-weight-light">时长</th>
          <th class="text-subtitle-2 font-weight-light">创建时间</th>
        </tr>
      </thead>
      <tbody>
      <tr v-for="item in items" :key="item.name">
        <td>
          <v-btn variant="text" color="purple" @click="gotoSong">{{item.name}}</v-btn>
        </td>
        <td>
          <v-btn variant="text" @click="gotoSinger">{{item.singer}}</v-btn>
        </td>
        <td>
          <v-btn variant="text" @click="gotoAlbum">{{item.album}}</v-btn>
        </td>
        <td class="text-caption">{{item.timeLength}}</td>
        <td>{{item.createTime}}</td>
      </tr>
      </tbody>
    </v-table>
    <v-pagination class="pa-4"
      v-model="page" :length="pageLength" size="small"
      active-color="purple"
    />
  </v-container>
</template>

<script lang="ts" setup>
import {reactive, toRefs} from "vue";

const state = reactive({
  totalResult: 10,
  items: [
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },
    {
      name: '青花瓷',
      singer: '周杰伦',
      album: '我很忙',
      timeLength: '3:20',
      createTime: '2020年2月2日'
    },

  ],
  page: 3,
  pageLength: 6
})
const {
  items, totalResult, pageLength, page
} = toRefs(state)

function gotoSong() {

}
function gotoSinger() {

}
function gotoAlbum() {

}
</script>

<style scoped>

</style>