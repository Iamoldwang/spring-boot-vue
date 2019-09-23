<template>
  <!--背景图-->



  <div class="note" :style="note">
    <div class="top-row">
      <span class="logo-text">西二旗科技大运动会管理系统</span>
    </div>
    <!--<div class="left-text">-->
      <!--<p>是雄鹰，就该博击长空，让啸声响彻云霄,；</p>-->
      <!--<p>是蛟龙，就能畅游四海，惊涛骇浪任我逍遥。</p>-->
      <!--<p>勇敢者，就应奔跑在那运动场上,让年轻的心奋发激昂。</p>-->
      <!--<p>真正的健儿，在那力竭之际，在那坎坷之时，在那荆棘丛中，</p>-->
      <!--<p>决不会停止迈动的双脚，挥一把汗水，洒一腔热血，昂首迈出坚毅的步伐</p>-->
    <!--</div>-->
    <div class="login-form">
      <el-form ref="loginForm" :model="account" :rules="rules" label-position="left" label-width="0px" class="demo-ruleForm login-container">
        <div class="titleSty">
          <el-col :span="8"><img src="../assets/logo.png" class="logoImg"></el-col>
          <el-col :span="16" class="login-text"><span>欢迎登录</span></el-col>
        </div>

        <el-form-item prop="name" style="margin-bottom: 30px;">
          <el-input prefix-icon="el-icon-user" type="text" v-model="account.name" auto-complete="off" placeholder="请输入您的账号"></el-input>
        </el-form-item>
        <el-form-item prop="password" style="margin-bottom: 30px;">
          <el-input prefix-icon="el-icon-lock" type="password" v-model="account.password" auto-complete="off" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <el-form-item prop="type" style="margin-bottom: 30px;">
          <el-radio v-model="account.type" label="1" border style="width: 120px;text-align: center;color:white;">学生</el-radio>
          <el-radio v-model="account.type" label="2" border style="float: right;width: 120px;text-align: center;color:white;">教师</el-radio>
        </el-form-item>
        <el-form-item style="width:100%;margin-bottom: 60px;">
          <el-row>
            <el-col :span="8">
              <el-button type="success" style="width: 95%;float: left;" @click.native.prevent="handleLogin" :loading="logining">登录</el-button>
            </el-col>
            <el-col :span="8">
              <span>&nbsp</span>
            </el-col>
            <el-col :span="8">
              <el-button type="warning" style="width:95%;float: right;" @click.native.prevent="handleRegister" :loading="logining">注册</el-button>
            </el-col>
          </el-row>

        </el-form-item>
      </el-form>
    </div>

    <el-dialog title="学生注册" :visible.sync="registerFormVisible" width="25%" top="20vh" :close-on-click-modal="false" @close="closeDialog">
      <el-form :model="registerForm" :rules="rules" ref="registerForm">
        <el-form-item prop="registerName" label="姓名" :label-width="formLabelWidth">
          <el-input prefix-icon="el-icon-user" v-model="registerForm.registerName" placeholder="请输入姓名" style="width: 300px;"></el-input>
        </el-form-item>
        <el-form-item prop="registerPassword" label="密码" :label-width="formLabelWidth">
          <el-input prefix-icon="el-icon-lock" v-model="registerForm.registerPassword" type="password" placeholder="请输入密码" show-password style="width: 300px;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="register":loading="logining">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

  const rules = {

    name:[{
      required: true,
      message: '请输入姓名',
      trigger: 'blur'
    },
    {
      min: 1,
      max: 100,
      message: '长度在 1 到 100 个字符',
      trigger: 'blur'
    }],
    password:[{
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    },
    {
      min: 6,
      max: 20,
      message: '长度在 6 到 20 个字符',
      trigger: 'blur'
    }],
    registerName:[{
      required: true,
      message: '请输入姓名',
      trigger: 'blur'
    },
      {
        min: 1,
        max: 100,
        message: '长度在 1 到 100 个字符',
        trigger: 'blur'
      }],
    registerPassword:[{
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    },
      {
        min: 6,
        max: 20,
        message: '长度在 6 到 20 个字符',
        trigger: 'blur'
      }]
  }

  let closeDialog = function(){
    this.registerFormVisible = false
    this.$refs.registerForm.resetFields()
  }
  let handleRegister = function(){
    this.registerForm = {}
    this.registerFormVisible = true
  }

  let handleLogin = function() {

    this.$refs.loginForm.validate((valid) => {
      if (valid) {

        this.logining = true

        let httpUrl = ''

        if(this.account.name == 'admin'){
          httpUrl = this.GLOBAL.httpRequestUrl + '/teacher/teacherLogin'
          sessionStorage.setItem('userType', 'admin');
        }else{
          if(this.account.type == '1'){
            httpUrl = this.GLOBAL.httpRequestUrl + '/student/studentLogin'
            sessionStorage.setItem('userType', 'student');
          }else{
            httpUrl = this.GLOBAL.httpRequestUrl + '/teacher/teacherLogin'
            sessionStorage.setItem('userType', 'teacher');
          }
        }

        var loginParams = {
          name: this.account.name,
          password: this.$md5(this.account.password)
        };

        this.$api.post(httpUrl, loginParams, response => {
          this.logining = false;
          if (response.data.code == 0) {


            var vo = response.data.content;
            sessionStorage.setItem('token', vo.token);
            sessionStorage.setItem('name', this.account.name);
            sessionStorage.setItem('userId', vo.id);
            this.$router.push({
              path: '/home'
            })

            this.$message({
              type: 'success',
              message: '欢迎登录：' + this.account.name
            })
            this.$common.recordLogs("系统登录")
          } else {
            this.$message({
              message: response.data.message,
              type: 'error'
            });
          }


        });
      } else {
        return false;
      }
    });
  }

  let register = function(){

    this.$refs.registerForm.validate((valid) => {
      if (valid) {

        this.logining = true

        var registerParams = {
          name: this.registerForm.registerName,
          password: this.$md5(this.registerForm.registerPassword)
        };

        this.$api.post(this.GLOBAL.httpRequestUrl + '/student/register', registerParams, response => {
          this.logining = false;
          if (response.data.code == 0) {

            this.$message({
              type: 'success',
              message: '注册成功'
            })

          } else {
            this.$message({
              message: response.data.message,
              type: 'error'
            });
          }
          this.registerFormVisible = false
        });
      } else {
        return false;
      }
    });
  }


  export default {
    name: 'login',
    data() {
      return {
        rules:rules,
        formLabelWidth:'100px',
        registerFormVisible:false,
        registerForm:{
          registerName:'',
          registerPassword:''
        },
        logining: false,
        note: {
          position: "absolute",
          top: "0px",
          left: "0px",
          width: "100%",
          height: "100%",
          backgroundImage: "url(" + require("../assets/bg.jpg") + ")",
          backgroundSize: "100% 100%",
          backgroundRepeat: "no-repeat",


        },
        account: {
          name: '',
          password: '',
          type:'1'
        },
        activeName:'first'

      };

    },
    methods: {
      register,
      closeDialog,
      handleRegister,
      handleLogin

    }
  }
</script>

<style scoped>
  .login-container {
    padding: 60px;
  }
  .titleSty{
    margin-bottom: 60px;
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    color: white;
    letter-spacing: 10px;
    height: 60px;
    line-height: 60px;
  }
  .logoImg{
    width: 65px;
    height: 60px;
    margin-left: 15px;
    float: left;
  }
  .top-row{
    position: absolute;
    z-index: 1000;
    margin-top: 10px;
  }
  .content-text-large{
    position: absolute;
    top: 300px;
    color: white;
    left: 100px;
    font-size: 45px;
    letter-spacing: 8px;
  }
  .content-text-small{
    position: absolute;
    top: 400px;
    color: white;
    left: 100px;
    font-size: 20px;
    width: 600px;
    letter-spacing: 6px;
    line-height: 50px;
  }
  .login-form{
    width: 400px;
    height: 500px;
    position: absolute;
    right: 100px;
    top: 50%;
    transform: translateY(-50%);
    background: #546075b5;
    border-radius: 10px;
    z-index: 999;
  }

  .logo-text{
    padding: 5px;
    color: rgb(55,55,55);
    font-size: 30px;
    display: block;
    margin-top: 30px;
    margin-left: 100px;
    border: 2px solid;
    border-image: -webkit-linear-gradient(left,rgb(48,230,255),rgb(6,40,60)) 30 30;
    border-image: -moz-linear-gradient(left,rgb(48,230,255),rgb(6,40,60)) 30 30;
    border-image: linear-gradient(left,rgb(48,230,255),rgb(6,40,60)) 30 30;
    letter-spacing: 3px;
  }
  .left-text{
    position: absolute;
    left: 100px;
    top: 300px;
    font-size: 25px;
  }
</style>
