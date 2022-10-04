<template>
  <v-app>
    <div class="login bg-deep-purple">
      <v-card class="mx-auto px-6 py-8" width="400" title="音乐播放器">
        <div v-if="!register">
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
        </div>
        <div v-else>
          <v-btn variant="text" color="purple" @click="gotoRegister(false)">返回</v-btn>
          <v-text-field color="purple" variant="underlined" v-model="form2.username"
                        :readonly="loading" class="mb-2" clearable
                        label="用户名" prepend-inner-icon="mdi-account"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form2.email"
                        :readonly="loading" class="mb-2" clearable
                        label="邮箱" prepend-inner-icon="mdi-account"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form2.code"
                        :readonly="loading" class="mb-2" clearable
                        label="验证码" prepend-inner-icon="mdi-account"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form2.password"
                        :readonly="loading" class="mb-2" clearable type="password"
                        label="密码" prepend-inner-icon="mdi-lock-outline"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form2.confirmPwd"
                        :readonly="loading" class="mb-2" clearable type="password"
                        label="确认密码" prepend-inner-icon="mdi-lock-outline"
                        :rules="require"/>
          <v-btn :disabled="!valid"
                 :loading="loading" style="width: 100%"
                 color="purple" @click.prevent="handleRegister"
                 size="large" variant="elevated">注册
          </v-btn>
        </div>
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
  valid2: false,
  form2: {
    username: '',
    email: '',
    code: '',
    password: '',
    confirmPwd: ''
  },
  loading: false,
  loading2: false,
  snackbar: false,
  rememberMe: false,

  reason: '',

  register: false
})

const {
  valid, valid2, form, form2, loading, loading2, snackbar, reason, rememberMe, register
} = toRefs(state)

const redirect = ref(undefined)

const require = ref([
  (v: string) => !!v || '请输入用户名'
])

function mount() {
  getCookie()
}

mount()

function gotoRegister(value: boolean) {
  register.value = value
}

function handleRegister() {
  loading2.value = true
  userStore
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