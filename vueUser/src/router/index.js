import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

import Login from '@/pages/Login'
import Main from '@/pages/Main'
import Dashboard from '@/pages/Dashboard'
import Student from '@/pages/Student'
import Score from '@/pages/Score'
import Teacher from '@/pages/Teacher'
import Log from '@/pages/Log'
import BasicData from '@/pages/BasicData'

let routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    component: Main,
    hidden: true,
    children: [{
      path: '/home',
      component: Dashboard,
      name: '系统管理'
    }]
  },
  {
    path: '/',
    name: '系统管理',
    component: Main,
    iconCls: 'fa fa-cog fa-fw',
    children: [
      {
        path: '/student',
        component: Student,
        name: '学生管理',
        auth:'student',
        iconCls: 'fa fa-user-o fa-fw'
      },
      {
        path: '/score',
        component: Score,
        name: '成绩管理',
        auth:'student',
        iconCls: 'fa fa-file-text-o fa-fw'
      },
      {
        path: '/teacher',
        component: Teacher,
        name: '教师管理',
        auth:'teacher',
        iconCls: 'fa fa-user-circle fa-fw'
      },
      {
        path: '/basic-data',
        component: BasicData,
        name: '基础数据',
        auth:'teacher',
        iconCls: 'fa fa-database fa-fw'
      },
      {
        path: '/log',
        component: Log,
        name: '日志管理',
        auth:'student',
        iconCls: 'fa fa-calendar-check-o fa-fw'
      }
    ]
  }
]

const router = new Router({
  mode: 'history',
  base: '/user/',
  routes: routes
})

router.beforeEach((to, from, next) => {
  console.log('--------------to.path:' + to.path)
  if (to.path.startsWith('/login')) {
    window.sessionStorage.removeItem('token')
    next()
  } else {
    let token = window.sessionStorage.getItem('token')
    if (!token) {
      //不存在token信息则说明用户未登录，跳转到登录页面，带上当前的页面地址，登录完成后做回跳，
      //判断是否是localhost：8080
      if(to.path != '/'){
        next({
          path: '/login',
          //将跳转的路由 path 作为参数，登录成功后跳转到该路由
          query: {redirect: to.fullPath}
        })
      }else{
        next('/login')
      }

    } else {
      next()
    }
  }
})

export default router
