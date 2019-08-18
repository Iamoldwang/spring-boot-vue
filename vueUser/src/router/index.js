import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

import Main from '@/pages/Main'
import Dashboard from '@/pages/Dashboard'
import User from '@/pages/User'
import Log from '@/pages/Log'

let routes = [
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
        path: '/user',
        component: User,
        name: '用户管理',
        iconCls: 'fa fa-user-o fa-fw'
      },
      {
        path: '/log',
        component: Log,
        name: '日志管理',
        iconCls: 'fa fa-file-text-o fa-fw'
      }
    ]
  }
]


const router = new Router({
  mode: 'history',
  base: '/user/',
  routes: routes
})

export default router
