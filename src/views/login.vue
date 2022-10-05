<template>
  <v-app>
    <div class="login bg-deep-purple">
      <v-card class="mx-auto px-6 py-8" width="400" title="音乐播放器">
        <v-form v-model="valid">
          <v-text-field color="purple" variant="underlined" v-model="form.email"
                        :readonly="loading" class="mb-2" clearable
                        label="邮箱" prepend-inner-icon="mdi-account"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form.password"
                        :readonly="loading" class="mb-2" clearable type="password"
                        label="密码" prepend-inner-icon="mdi-lock-outline"
                        :rules="require"/>
          <v-row>
            <v-checkbox style="height: 60px; padding-left: 10px" v-model="rememberMe" density="compact" hide-details label="记住密码"/>
            <v-btn variant="text" color="purple" @click="gotoRegister(true)">注册账号</v-btn>
          </v-row>
          <v-btn :disabled="!valid"
                 :loading="loading" style="width: 100%"
                 color="purple" @click.prevent="handleLogin"
                 size="large" variant="elevated">登录
          </v-btn>
        </v-form>
      </v-card>
      <v-snackbar v-model="snackbar">
        {{reason}}
        <template #actions>
          <v-btn color="pink"
                 variant="text" @click="snackbar = false">
            Close
          </v-btn>
        </template>
      </v-snackbar>
    </div>
  </v-app>


</template>

<script lang="ts" setup>

import useUserStore from "../store/modules/user";
import {useRouter} from "vue-router";
import {onMounted, reactive, ref, toRefs} from "vue";
import myRouter from '../router'
import Cookies from "js-cookie";
import {decrypt, encrypt} from "../utils/jsencrypt";

const userStore = useUserStore()
const router = useRouter()

const state = reactive({
  valid: false,
  form: {
    email: '',
    password: ''
  },

  loading: false,

  snackbar: false,
  rememberMe: false,

  reason: '',
})

const {
  valid, form, loading, snackbar, reason, rememberMe
} = toRefs(state)

const redirect = ref(undefined)

const require = ref([
  (v: string) => !!v || '请输入内容'
])

function mount() {
  getCookie()
}

onMounted(() => mount())

function gotoRegister(value: boolean) {
  myRouter.push({ path: '/register' })
}


function handleLogin() {
  loading.value = true
  if(rememberMe.value){
    Cookies.set("username", form.value.email, { expires: 30 });
    Cookies.set("password", String(encrypt(form.value.password)), { expires: 30 });
    Cookies.set("rememberMe", rememberMe.value + "", { expires: 30 });
  } else {
    Cookies.remove("username");
    Cookies.remove("password");
    Cookies.remove("rememberMe");
  }
  userStore.login(form.value).then(() => {
    myRouter.push({ path: redirect.value || "/" })
  }).catch(msg => {
    loading.value = false
    snackbar.value = true
    reason.value = msg
  })
}

function getCookie() {
  const username = Cookies.get("username")
  const password = Cookies.get("password")
  const rememberMe = Cookies.get("rememberMe");
  if(username !== undefined) {
    form.value.email = username
  }
  if(password !== undefined) {
    form.value.password = String(decrypt(password))
  }
  if(rememberMe !== undefined) {
    state.rememberMe = Boolean(rememberMe)
  }
}

</script>

<style lang="scss" scoped>
.login {
  display: flex;
  height: 100%;
  background-size: cover;
  justify-content: center;
  align-items: center;
}
</style>