<template>
  <v-app>
    <div class="login bg-deep-purple">
      <v-card class="mx-auto px-6 py-8" width="400" title="音乐播放器">
        <v-form v-model="valid">
          <v-btn variant="text" color="purple" @click="gotoLogin()">返回</v-btn>
          <v-text-field color="purple" variant="underlined" v-model="form.username"
                        :readonly="loading" class="mb-2" clearable
                        label="用户名" prepend-inner-icon="mdi-account"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form.email"
                        :readonly="loading" class="mb-2" clearable
                        label="邮箱" prepend-inner-icon="mdi-email"
                        :rules="require"/>
          <v-row>
            <v-col cols="9">
              <v-text-field color="purple" variant="underlined" v-model="form.code"
                            :readonly="loading" class="mb-2" clearable
                            :label="hintText" prepend-inner-icon="mdi-numeric"
                            :rules="require"/>
            </v-col>
            <v-col cols="3">
              <v-btn :disabled="codeDisable" @click="handleCode"
                     style="width: 20%">获取</v-btn>
            </v-col>
          </v-row>

          <v-text-field color="purple" variant="underlined" v-model="form.password"
                        :readonly="loading" class="mb-2" clearable type="password"
                        label="密码" prepend-inner-icon="mdi-lock-outline"
                        :rules="require"/>
          <v-text-field color="purple" variant="underlined" v-model="form.confirmPwd"
                        :readonly="loading" class="mb-2" clearable type="password"
                        label="确认密码" prepend-inner-icon="mdi-lock-outline"
                        :rules="confirmRequire"/>
          <v-btn :disabled="!valid"
                 :loading="loading" style="width: 100%"
                 color="purple" @click.prevent="handleRegister"
                 size="large" variant="elevated">注册
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
import {reactive, ref, toRefs} from "vue";
import {getCode, register} from "../api/register";
import myRouter from "../router";
import {Register} from "../types/register";

const state = reactive({
  valid: false,
  form: {
    username: '',
    email: '',
    code: '',
    password: '',
    confirmPwd: ''
  },
  loading: false,
  codeDisable: false,
  hintText: '验证码',
  snackbar: false,
  reason: '',
  mark: ''
})

const {
  valid, form, loading, codeDisable, snackbar, reason, mark, hintText
} = toRefs(state)

const require = ref([
  (v: string) => !!v || '该输入框不能为空'
])

const confirmRequire = ref([
  (v: string) => {
    if(!v || v !== form.value.password) {
      return '两次输入密码不一致！'
    }
    return true
  }
])

function mount() {

}
mount()

function handleCode() {
  mark.value = ''
  for (let i = 0; i < 6; i++) {
    mark.value += Math.floor(Math.random() * 10)
  }
  console.log(mark)
  if(form.value.email === '') {
    snackbar.value = true
    reason.value = '邮箱不能为空！'
    return
  }
  getCode(form.value.email, mark.value).then(() => {
    codeDisable.value = true
    hintText.value = '60秒后可重新获取'
    setTimeout(() => {
      codeDisable.value = false
      hintText.value = '验证码'
    }, 1000 * 60);
  }).catch(e => {
    reason.value = e
    snackbar.value = true
  })
}

function handleRegister() {
  if(!valid) return
  const data: Register = {
    username: form.value.username,
    password: form.value.password,
    code: form.value.code,
    email: form.value.email,
    mark: mark.value
  }
  register(data).then(() => {
    alert('注册成功！')
    myRouter.push({ path: '/login' })
  }).catch(e => {
    reason.value = e
    snackbar.value = true
  })
}

function gotoLogin() {
  myRouter.push({ path: '/login' })
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