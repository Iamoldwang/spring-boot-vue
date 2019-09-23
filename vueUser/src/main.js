// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false

import global_ from './components/Global'
Vue.prototype.GLOBAL = global_

//引入font-awesome
import 'font-awesome/css/font-awesome.min.css'
//引入ElementUI
import ElementUI from 'element-ui'
//引入自定义主题
import './assets/theme/element-#E3BA63/index.css'
Vue.use(ElementUI)

import axios from 'axios'
Vue.prototype.$axios = axios

import Api from './axios/axios.js';
Vue.prototype.$api = Api;

import common from './utils/common.js'
Vue.prototype.$common = common;

import md5 from 'js-md5'
Vue.prototype.$md5 = md5;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
