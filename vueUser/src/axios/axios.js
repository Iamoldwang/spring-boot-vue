import axios from 'axios'
import {
  Message
} from 'element-ui';
import global_ from '../components/Global'
import router from '../router'

// import Cookies from 'js-cookie'
//axios.defaults.baseURL = 'http://127.0.0.1:8090/club';

// 环境的切换

//let token = sessionStorage.getItem("token");
let http = axios.create({
  baseURL: global_.httpRequestUrl,
  withCredentials: false,
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
  },
  transformRequest: [function(data) {
    let newData = '';
    for (let k in data) {
      if (data.hasOwnProperty(k) === true) {
        newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
      }
    }
    return newData;
  }]
});


// http request 拦截器
http.interceptors.request.use(
  config => {
    console.log(config.url);
    if(config.url.indexOf('/login/userLogin') >= 0){
      return config;
    }else{
      let token = sessionStorage.getItem("token");
      if (token) {
        config.headers.token = token;
      }
      return config;
    }
  },
  err => {
    return Promise.reject(err);
  });

/*
 * Promise(ES6语法糖)
 * 他是一个对象，是用来处理异步操作的，可以让我们写异步调用的时候写起来更加优雅，更加美观便于阅读。
 * 顾名思义为承诺、许诺的意思，意思是使用了Promise之后他肯定会给我们答复，无论成功或者失败都会给我们一个答复，所以我们就不用担心他跑了哈哈。
 * 所以，Promise有三种状态：pending（进行中），resolved（完成），rejected（失败）。
 * 只有异步返回的结构可以改变其状态。所以，promise的过程一般只有两种：
 * pending->resolved(完成)
 * pending->rejected(失败)
 *
 * */
http.interceptors.response.use(
  res  => {
    if(res.data.code == 2 || res.data.code == 3){

      Message({
        showClose: true,
        message: '登录信息失效，请重新登录!',
        type: 'error',
        duration: 2000
      })

      //携带当前页面路由，以在登录页面完成登录后返回当前页面
      router.replace({
        path: '/login',
        query: {
          redirect: router.currentRoute.fullPath
        }
      });
    }

    if (res.status === 200) {
      return Promise.resolve(res);
    } else {
      return Promise.reject(res);
    }
  },
  err => {
    return Promise.reject(err);
  });

function apiAxios(method, url, params, response) {
  http({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
  }).then(function(res) {
    response(res);
  }).catch(function(err) {
    response(err);
  })
}

export default {
  get: function(url, params, response) {
    return apiAxios('GET', url, params, response)
  },
  post: function(url, params, response) {
    return apiAxios('POST', url, params, response)
  },
  put: function(url, params, response) {
    return apiAxios('PUT', url, params, response)
  },
  delete: function(url, params, response) {
    return apiAxios('DELETE', url, params, response)
  }
}
