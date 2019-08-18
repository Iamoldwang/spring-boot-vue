<template>
  <section>
    <el-container class="container">
      <!--左边-->
      <el-aside :width="'200px'">
        <el-container>
          <el-header>
            <img src="../assets/logo.png" class="logoImg">
          </el-header>
          <el-main>
            <el-menu :default-active="$route.path" :collapse="collapsed">
              <template v-for="(item,index) in menus">
                <el-submenu :index="index+''" v-if="!item.leaf">
                  <template slot="title">
                    <i :class="item.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>
                    <span>{{item.name}}</span>
                  </template>
                  <el-menu-item
                    v-for="child in item.children"
                    :index="child.path"
                    :key="child.path"
                    @click="$router.push(child.path)">
                    <i :class="child.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>{{child.name}}
                  </el-menu-item>
                </el-submenu>
                <el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path">
                  <i :class="item.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>{{item.children[0].name}}
                </el-menu-item>
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
            <el-col :span="20" class="header-title">
              <span class="system-name">{{systemName}}</span>

            </el-col>
            <el-col :span="2"><span class="el-dropdown-link userinfo-inner">你好：{{userName}}</span></el-col>
            <el-col :span="1"><span class="el-dropdown-link userinfo-inner">|</span></el-col>
            <el-col :span="1"><span class="el-dropdown-link userinfo-inner">退出</span></el-col>
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
      systemName: '用户管理系统',
      userName: 'admin',
      menus: [],
      transitionName:'',
      isLoading:true
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

  export default {
    data: data,
    methods: {
      initMenu
    },
    mounted: function() {
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
    padding: 0;
  }
  }
  }

  .main {
    width: $width;
    height: $height;
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
  span {
    padding: 0 20px;
  }
  }
  }

  .system-name {
    font-size: large;
    font-weight: bold;
  }
  }
  .logoImg{
    width: 50px;
    height: 50px;
    border-radius: 30px;
    margin-top: 5px;
  }

  .mainDiv{
    min-height: 700px;
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

</style>
