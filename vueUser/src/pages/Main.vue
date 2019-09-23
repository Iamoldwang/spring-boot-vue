<template>
  <section>
    <el-container class="container">
      <!--左边-->
      <el-aside :width="'200px'">
        <el-container>
          <el-header>
            <img src="../assets/logo.png" class="logoImg" @click="$router.push('/home')">
          </el-header>
          <el-main>
            <el-menu :default-active="$route.path" :collapse="collapsed">
              <template v-for="(item,index) in menus">
                <el-submenu :index="index+''" v-if="!item.leaf">
                  <template slot="title">
                    <i :class="item.iconCls" style="margin-right: 6px;"></i>
                    <span>{{item.name}}</span>
                  </template>
                  <el-menu-item
                    v-for="child in item.children"
                    v-if="currentLoginType == child.auth || currentLoginType == 'admin' || currentLoginType == 'teacher'"
                    :index="child.path"
                    :key="child.path"
                    @click="$router.push(child.path)">
                    <i :class="child.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>{{child.name}}
                  </el-menu-item>
                </el-submenu>
                <!--<el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path">-->
                  <!--<i :class="item.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>{{item.children[0].name + 'sss'}}-->
                <!--</el-menu-item>-->
              </template>
            </el-menu>
          </el-main>
        </el-container>
      </el-aside>
      <!--内容-->
      <el-container>
        <!--页眉-->
        <el-header class="header">
          <el-row>
            <el-col :span="16" class="header-title">
              <span class="system-name" @click="$router.push('/home')">西二旗科技大学运动会管理系统</span>
            </el-col>

            <el-col :span="8" style="font-size: 16px;text-align: right;">
              <el-dropdown>
                <el-button type="primary btn-transparent top-right-btn">
                  <i class="el-icon-time"></i>  {{currentTime}}
                </el-button>
              </el-dropdown>

              <el-dropdown>
                <el-button type="primary btn-transparent top-right-btn">
                  <i class="el-icon-sunny"></i>  33℃
                </el-button>
              </el-dropdown>

              <el-dropdown>
                <el-button type="primary btn-transparent top-right-btn">
                  <i class="el-icon-user"></i>  {{currentName}}
                </el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>个人中心</el-dropdown-item>
                  <el-dropdown-item>我的日程</el-dropdown-item>
                  <el-dropdown-item>我的消息</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>

              <el-dropdown>
                <el-button type="primary btn-transparent top-right-btn" @click="logout">
                  <i class="el-icon-switch-button"></i>  退出
                </el-button>
              </el-dropdown>
            </el-col>

          </el-row>
        </el-header>
        <!--中间-->
        <el-main class="main">
          <transition :name="transitionName" mode="out-in">
            <router-view></router-view>
          </transition>
        </el-main>
      </el-container>
    </el-container>
  </section>
</template>

<script>
  let data = () => {
    return {
      collapsed: false,
      userName: 'admin',
      menus: [],
      transitionName:'',
      isLoading:true,
      currentTime:'',
      currentName:'',
      currentLoginType:''
    }
  }

  let initMenu = function() {
    for (let i in this.$router.options.routes) {
      let root = this.$router.options.routes[i]
      if (root.hidden)
        continue
      let children = []
      for (let j in root.children) {
        let item = root.children[j]
        if (item.hidden)
          continue
        children.push(item)
      }
      if (children.length < 1)
        continue

      this.menus.push(root)

      root.children = children
    }
  }

  
  let logout = function () {
    this.$confirm('确定退出系统吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {


      this.$common.recordLogs("系统退出")

      this.$api.post(this.GLOBAL.httpRequestUrl + '/student/studentLogout', null, res => {
        if (res.data.code == 0) {

          sessionStorage.setItem('userType', '');
          sessionStorage.setItem('token', '');
          sessionStorage.setItem('name', '');
          sessionStorage.setItem('userId', '');

          this.$message({
            type: 'success',
            message: '退出成功！'
          })

          this.$router.push({
            path: '/login'
          });
        } else {
          this.$message({
            type: 'error',
            message: '退出失败！'
          })
        }
      });

    }).catch(e => {})
  }
  export default {
    data: data,
    methods: {
      initMenu,
      logout
    },
    mounted: function() {
      this.currentTime = this.$common.getCurrentFormatDate('yyyy-MM-dd HH:mm:ss')
      this.currentName = sessionStorage.getItem('name');
      this.currentLoginType = sessionStorage.getItem('userType');
      this.initMenu()
    },
    watch: {//使用watch 监听$router的变化
      $route(to, from) {
        this.transitionName = 'slide-right';
      }
    }
  }
</script>

<style scoped="scoped" lang="scss">
  $width: 100%;
  $height: 100%;
  $background-color: #E3BA63;
  $header-color: #fff;
  $header-height: 60px;

  .container {
    position: absolute;
    top: 0;
    bottom: 0;
    width: 100%;
  .el-aside {
  .el-header {
    line-height: $header-height;
    background-color: $background-color;
    color: $header-color;
    text-align: center;
  }
  .el-container {
    height: $height;
  .el-main {
    padding: 0px !important;
  }
  }
  }

  .main {
    width: $width;
    height: $height;
  }
  .el-main{
    padding: 0px !important;
  }

  .menu-button {
    width: 14px;
    cursor: pointer;
  }

  .userinfo-inner {
    cursor: pointer;
  }

  .el-menu {
    height: $height;
  }

  .header {
    background-color: $background-color;
    color: $header-color;
    text-align: center;
    line-height: $header-height;
    padding: 0;

  .header-title {
    text-align: left;
    color:rgb(43,69,140);
  span {
    padding: 0 20px;
  }
  }
  }

  .system-name {
    font-size: 20px;
    font-weight: bold;
  }
  }
  .logoImg{
    width: 64px;
    height: 60px;
  }

  .mainDiv{
    min-height: 750px;
    margin: 30px;
    background-color: #fff;
    box-shadow: 0 5px 20px rgba(220,222,231,.65);
    padding: 30px;
  }

  @keyframes slideInLeft {
    from {
      transform: translate3d(100%, 0, 0);
      opacity: 1;
    }
    to {
      transform: translate3d(0, 0, 0);
      opacity: 1;
    }
  }
  @keyframes slideInRight {
    from {
      transform: translate3d(-100%, 0, 0);
      opacity: 1;
    }
    to {
      transform: translate3d(0, 0, 0);
      opacity: 1;
    }
  }
  @keyframes slideLeftOut {
    from {
      transform: translate3d(0, 0, 0);
      opacity: 0;
    }
    to {
      transform: translate3d(100%, 0, 0);
      opacity: 0;
    }
  }
  @keyframes slideRightOut {
    from {
      transform: translate3d(0, 0, 0);
      opacity: 0;
    }
    to {
      transform: translate3d(-100%, 0, 0);
      opacity: 0;
    }
  }
  .slide-left-enter-active {
    animation: slideInLeft .3s forwards;
    z-index:5;
  }

  .slide-left-leave-active {
    animation: slideLeftOut .3s forwards;
    z-index:3;
  }
  .slide-right-enter-active {
    animation: slideInRight .3s forwards;
    z-index:5;
  }

  .slide-right-leave-active {
    animation: slideRightOut .3s forwards;
    z-index:3;
  }

  .top-right-btn{
    color:rgb(43,69,140);
  }

  .el-submenu__title{
    padding-left: 35px !important;
  }

</style>
