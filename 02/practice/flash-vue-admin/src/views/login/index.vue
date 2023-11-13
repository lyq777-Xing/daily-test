<template>
  <div
    class="login-container"
    style="background-image: url(./img/bg3.png);background-repeat:np-repeat;background-size:100%"
  >

    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div style="width: 100%;height: 430px;border-radius: 8px;background:rgba(255,255,255,0.3);display: flex;justify-content: center;align-items: center;">
        <div style="width: 95%;height: 410px;border-radius: 8px;background:rgba(255,255,255,0.9);">
          <div class="title-container">
            <h3
              class="title"
              style="color:#171717"
            >图书馆管理系统</h3>

          </div>
          <div style="position: relative;width:80%;margin:auto">
            <el-form-item prop="username">
              <span class="svg-container">
                <svg-icon icon-class="user" />
              </span>
              <el-input
                v-model="loginForm.username"
                :placeholder="$t('login.username')"
                name="username"
                type="text"
                auto-complete="on"
              />
            </el-form-item>

            <el-form-item prop="password">
              <span class="svg-container">
                <svg-icon icon-class="password" />
              </span>
              <el-input
                v-model="loginForm.password"
                :type="pwdType"
                :placeholder="$t('login.password')"
                name="password"
                auto-complete="on"
                @keyup.enter.native="handleLogin"
              />
              <span
                class="show-pwd"
                @click="showPwd"
              >
                <svg-icon icon-class="eye" />
              </span>
            </el-form-item>

            <el-button
              :loading="loading"
              type="primary"
              style="width: 100%; margin-top: 30px"
              @click.native.prevent="handleLogin"
            > {{ $t("login.logIn") }} </el-button>

          </div>

        </div>
      </div>
    </el-form>

  </div>
</template>

<script src="./login.js"></script>
<style rel="stylesheet/scss" lang="scss" src="./login.scss"></style>
