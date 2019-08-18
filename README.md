
## Spring Boot Vue Element入门实战(持续更新)
[![maven](https://img.shields.io/badge/Maven-3.0+-green.svg)](https://search.maven.org/search?q=g:org.apache.maven) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://gitee.com/Iamoldwang/spring-boot-vue/blob/master/LICENSE) [![](https://img.shields.io/badge/JDK-1.8+-red.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) [![](https://img.shields.io/badge/SpringBoot-2.0+-purple.svg)](https://search.maven.org/search?q=g:org.springframework.boot) [![maven](https://img.shields.io/badge/Vue-2.0+-red.svg)](https://cn.vuejs.org/) [![maven](https://img.shields.io/badge/Element-2.7.0+-yellow.svg)](https://element.eleme.cn/#/zh-CN) [![maven](https://img.shields.io/badge/Redis-5.0+-yellow.svg)](https://redis.io/)



最近在自学VUE，发现这个前端框架很火，他和传统的Jquery 编程思路完全不一样，Jquery 是事件驱动的，而VUE是数据驱动的。以前写页面经常是通过Jquery 操作各种DOM，而VUE不用对DOM进行操作，直接操作绑定的数据即可。接下来我会利用业余时间，把我学习到的分享给大家，知识纯属自学，如有错误的地方欢迎指出，共同进步！


## 入门实战目录
[Spring Boot Vue Element入门实战（一）开发环境搭建](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（二）前端框架搭建](https://blog.csdn.net/woshiwangdaye/article/details/89765942)

[Spring Boot Vue Element入门实战（三）Vue Devtools安装](https://blog.csdn.net/woshiwangdaye/article/details/97617317)

[Spring Boot Vue Element入门实战（四）主页面开发](https://blog.csdn.net/woshiwangdaye/article/details/89792521)

[Spring Boot Vue Element入门实战（五）封装axios](https://blog.csdn.net/woshiwangdaye/article/details/99292624)

[Spring Boot Vue Element入门实战（六）常用工具类封装](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（七）Echart集成](https://blog.csdn.net/u013254183/article/details/99634362)

[Spring Boot Vue Element入门实战（八）静态列表页面](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（九）router路由配置](https://blog.csdn.net/woshiwangdaye/article/details/99679492)

[Spring Boot Vue Element入门实战（十）Vue生命周期](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十一）后端服务搭建](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十二）数据库设计](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十三）增删改查接口](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十四）登录，路由拦截](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十五）前后台整合](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十六）Nginx+Tomcat部署](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（十七）总结](https://blog.csdn.net/woshiwangdaye/article/details/89455625)


## 技术架构

技术/框架/工具     | 说明
-------- | -----
Axios| HTTP 库，类似AJAX
Element UI| Vue 2.0 组件库
Vue Router  | VUE 路由管理器
Echart  | 图表插件
Spring Boot| 项目开发框架
Mybatis| 持久层框架
Druid| 数据库连接池
Swagger  | API文档工具
Redis| NoSql数据库
Mysql| 关系型数据库
Maven| 项目构建工具
IDEA| 前后端开发工具
Navicat| 数据库管理工具
Redis Desktop Manager  | Redis桌面管理工具
Power Designer  | 数据库建模工具


## 主要功能

  * 登录
    + 输入用户名密码，以及验证码点击登录进入系统
  * 退出
  	+ 点击退出按钮退出系统到登录页面
  * 查看用户列表
  	+ 点击左侧菜单进入用户列表
  * 添加用户
  	+ 点击添加按钮，填写用户信息，保存添加
  * 修改用户
  	+ 点击修改按钮，修改用户信息，保存修改
  * 锁定用户
  	+ 锁定用户之后用户无法登录系统，但用户记录还存在
  * 删除用户
  	+ 删除用户之后，改用户所有记录被删除
  * 用户查询
  	+ 可以根据用户名，类型查询
  	
- 系统日志
  * 日志列表
    + 列出系统登录登出，用户操作日志列表
  * 日志查询
    + 可以根据时间段，日志类型查询数据
  * 趋势图
    + 列出Echart日志趋势图

## 开源交流

- [我的CSDN博客-mingnianshimanian](https://blog.csdn.net/woshiwangdaye)
- [我的简书](https://www.jianshu.com/u/dfef29a4a6ff)
- [我的邮箱](wangpanfigo@126.com)
- 我的QQ：509961766
- 微信公众号
![](https://img-blog.csdnimg.cn/20190422172523259.png)

## 项目结构

```
. VUE
├── build              构建脚本目录
├── config             项目配置目录
├── node_modules       项目依赖目录
├── src                项目源码目录
│   └── assets         资源目录
│   └── components     组件目录
│   └── router         路由目录
│   └── App.vue        页面vue组件
│   └── main.js        页面js入口
├── static             项目静态资源目录
├── test               项目测试目录
├── index.html         主页面入口
├── package.json       项目描述文件
```

## 开源许可

`spring-boot-vue`采用`Apache2`开源许可。
