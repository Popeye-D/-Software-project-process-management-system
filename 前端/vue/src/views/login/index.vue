<template>
  <div class="login-container">
    <el-form autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left"
             label-width="0px"
             class="card-box login-form">
      <h3 class="title">软件项目过程管理系统</h3>
      <el-form-item prop="username">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user"/>
        </span>
        <el-input v-model="loginForm.username" autoComplete="on"/>
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"></svg-icon>
        </span>
        <el-input type="password" @keyup.enter.native="handleLogin" v-model="loginForm.password"
                  autoComplete="on"></el-input>
      </el-form-item>

        
    <el-form-item >  <input type="text" placeholder="请输入验证码" class="yanzhengma_input" v-model="picLyanzhengma">
                    <input id="verification" type="button" placeholder="获取验证码" @click="createCode"  class="verification" v-model="checkCode"/> 
    
                    <div class="verify-wrap" id="verify-wrap2"></div><button type="button" id="resetBtn2" style="display: inline-block;">重置</button>
     </el-form-item>
     
      <el-form-item> 

        <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleLogin">
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default {
    name: 'login',
    data() {
      return {
        checkCode:'',
        picLyanzhengma:'',

        loginForm: {
          username: '',
          password: ''
        },
        loginRules: {
          username: [{required: true, trigger: 'blur', message: "请输入用户名"}],
          password: [{required: true, trigger: 'blur', message: "请输入密码"}]
        },
        loading: false
      }
    },
    methods: {
      //验证码
      mounted(){
      if (this.$route.query.loginForm){
        this.loginForm = this.$route.query.loginForm
      }
      if(this.$$route.query.username){
         this.us  = this.$route.query.username
      }
      if(this.$route.query.loginForm === 'login'){
        this.initCouponInfo()
      }
      else{
        this.$message.error("验证码错误");
      }

      },
      created(){
        this.createCode();
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('Login', this.loginForm).then(data => {
              this.loading = false
              if ("success" === data.result) {
                this.$router.push({path: '/'})
              } else {
                this.$message.error("账号/密码错误");
              }
            }).catch(() => {
              this.loading = false
            })
          } else {
            return false
          }
        })
      },

   
// 图片验证码
    createCode(){
                 //先清空验证码的输入
                 this.code = "";
                 this.checkCode = "";
                 this.picLyanzhengma = "";
                 //验证码的长度  
                    var codeLength = 4; 
                    //随机数 
          var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');  
          for(var i = 0; i < codeLength; i++) {
                        //取得随机数的索引（0~35）
                            var index = Math.floor(Math.random()*36);   
                            //根据索引取得随机数加到code上
              this.code += random[index];   
                    }
                    //把code值赋给验证码  
          this.checkCode = this.code; 
      }
}
    //
    


  }
</script>
<style rel="stylesheet/scss" lang="scss">
  @import "../../styles/mixin.scss";
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    @include relative;
    height: 100vh;
    background-color: $bg;
    input:-webkit-autofill {
      // -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
      -webkit-text-fill-color: #fff !important;
    }
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
    }
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
    }
    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
      &_login {
        font-size: 20px;
      }
    }
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 400px;
      padding: 35px 35px 15px 35px;
      margin: 120px auto;
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
    }
    .thirdparty-button {
      position: absolute;
      right: 35px;
      bottom: 28px;
    }
  }
  
.verification{
  background-color: #eee;
  width: 60px;
}
</style>
