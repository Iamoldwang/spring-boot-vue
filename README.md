
## Spring Boot Vue Element入门实战(完结)
[![maven](https://img.shields.io/badge/Maven-3.0+-green.svg)](https://search.maven.org/search?q=g:org.apache.maven) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://gitee.com/Iamoldwang/spring-boot-vue/blob/master/LICENSE) [![](https://img.shields.io/badge/JDK-1.8+-red.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) [![](https://img.shields.io/badge/SpringBoot-2.0+-purple.svg)](https://search.maven.org/search?q=g:org.springframework.boot) [![maven](https://img.shields.io/badge/Vue-2.0+-red.svg)](https://cn.vuejs.org/) [![maven](https://img.shields.io/badge/Element-2.7.0+-yellow.svg)](https://element.eleme.cn/#/zh-CN) [![maven](https://img.shields.io/badge/Redis-5.0+-yellow.svg)](https://redis.io/)



最近给朋友做一个大学运动会管理系统，用作教学案例，正好自己也在自学VUE，决定用spring boot vue做一个简单的系统。vue这个前端框架很火，他和传统的Jquery 编程思路完全不一样，Jquery 是事件驱动的，而VUE是数据驱动的。以前写页面经常是通过Jquery 操作各种DOM，而VUE不用对DOM进行操作，直接操作绑定的数据即可。接下来我会利用业余时间，把我学习到的分享给大家，知识纯属自学，如有错误的地方欢迎指出，共同进步！


## 入门实战目录
[Spring Boot Vue Element入门实战（一）开发环境搭建](https://blog.csdn.net/woshiwangdaye/article/details/89455625)

[Spring Boot Vue Element入门实战（二）前端框架搭建](https://blog.csdn.net/woshiwangdaye/article/details/89765942)

[Spring Boot Vue Element入门实战（三）Vue Devtools安装](https://blog.csdn.net/woshiwangdaye/article/details/97617317)

[Spring Boot Vue Element入门实战（四）主页面开发](https://blog.csdn.net/woshiwangdaye/article/details/89792521)

[Spring Boot Vue Element入门实战（五）封装axios](https://blog.csdn.net/woshiwangdaye/article/details/99292624)

[Spring Boot Vue Element入门实战（六）常用工具类封装](https://blog.csdn.net/u013254183/article/details/99455052)

[Spring Boot Vue Element入门实战（七）Echart集成](https://blog.csdn.net/u013254183/article/details/99634362)

[Spring Boot Vue Element入门实战（八）静态列表页面](https://blog.csdn.net/u013254183/article/details/99647513)

[Spring Boot Vue Element入门实战（九）router路由配置](https://blog.csdn.net/woshiwangdaye/article/details/99679492)

[Spring Boot Vue Element入门实战（十）Vue生命周期](https://blog.csdn.net/woshiwangdaye/article/details/99709143)

[Spring Boot Vue Element入门实战（十一）后端SpringBoot服务搭建](https://blog.csdn.net/u013254183/article/details/100040765)

[Spring Boot Vue Element入门实战（十二）PowerDesigner数据库建模](https://blog.csdn.net/u013254183/article/details/100275544)

[Spring Boot Vue Element入门实战（十三）Spring Boot+Mybatis+Redis+Swagger](https://blog.csdn.net/u013254183/article/details/100543795)

[Spring Boot Vue Element入门实战（十四）前后台整合增删改查](https://blog.csdn.net/u013254183/article/details/101062182)

[Spring Boot Vue Element入门实战（十五）注册登录路由跨域拦截](https://blog.csdn.net/u013254183/article/details/101064422)

[Spring Boot Vue Element入门实战（十六）菜单首页，前后台整合](https://blog.csdn.net/u013254183/article/details/101066518)

[Spring Boot Vue Element入门实战（十七）Nginx+Tomcat前后端部署](https://blog.csdn.net/u013254183/article/details/101067463)

[Spring Boot Vue Element入门实战（十八）完结篇](https://blog.csdn.net/u013254183/article/details/101271073)



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

  > 运动会管理系统功能模块说明

- 系统首页
  * 显示运动会概况数据，参赛人数，金银铜排行榜，比赛日，今日重点项目等等数据
- 学生管理
  * 登录
    + 输入学生姓名密码（MD5双重加密），以及验证码点击登录获取Token进入系统
  * 退出
  	+ 点击退出按钮退出系统到登录页面并清除缓存
  * 查看学生列表
  	+ 点击左侧菜单进入学生列表
  * 添加学生
  	+ 点击添加按钮，填写学生信息，保存添加
  * 修改学生
  	+ 点击修改按钮，修改学生信息，保存修改
  * 锁定学生
  	+ 锁定学生之后学生无法登录系统，但学生记录还存在
  * 删除学生
  	+ 删除学生之后，该学生所有记录被删除
  * 学生查询
  	+ 可以根据学生姓名，学号查询数据

- 教师管理
  * 登录
    + 输入教师姓名密码（MD5双重加密），以及验证码点击登录获取Token进入系统
  * 退出
  	+ 点击退出按钮退出系统到登录页面并清除缓存
  * 查看教师列表
  	+ 点击左侧菜单进入教师列表
  * 添加教师
  	+ 点击添加按钮，填写教师信息，保存添加
  * 修改教师
  	+ 点击修改按钮，修改教师信息，保存修改
  * 锁定教师
  	+ 锁定教师之后教师无法登录系统，但教师记录还存在
  * 删除教师
  	+ 删除教师之后，该教师所有记录被删除
  * 教师查询
  	+ 可以根据教师姓名，编号查询数据
  
- 成绩管理
  * 查看成绩列表
  	+ 点击左侧菜单进入成绩列表
  * 添加成绩
  	+ 点击添加按钮，填写成绩信息，保存添加
  * 修改成绩
  	+ 点击修改按钮，修改成绩信息，保存修改
  * 删除成绩
  	+ 删除成绩之后，该成绩所有记录被删除
  * 成绩查询
  	+ 可以根据学生姓名，学号，项目查询学生成绩
  	
- 系统日志
  * 日志列表
    + 列出系统登录登出，用户操作日志列表
  * 日志查询
    + 可以根据时间段，日志类型查询数据
  * 趋势图
    + 列出Echart日志趋势图
    
- 基础数据
  * 基础数据添加
    + 点击添加按钮，填写数据信息，保存添加
  * 基础数据修改
    + 点击修改按钮，修改数据信息，保存修改
  * 基础数据删除
    + 删除数据之后，该数据所有记录被删除


> 运动会管理系统人员权限说明

- 学生：需要先注册，登录进入首页，只能查看学生信息，成绩信息，不能查看教师信息，没有增删改权限
- 教师：登录进入首页，可以增删改查，审核学生信息，成绩信息，可以查看教师信息，不能编辑教师信息
- 超级管理员（only one）：可以增删改查所有数据，自己不能被删除

![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/141422_0027d633_1744678.png "屏幕截图.png")

用户名     | 密码  | 类型
-------- | ----- | -----
xiaomidou  | 123456  | 学生
teacher| 123456    | 教师
admin| 123456  | 超级管理员

## 系统页面

![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142539_1eedefbb_1744678.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142549_2ca57245_1744678.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142554_a1aaabdc_1744678.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142604_1abaf86e_1744678.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142613_35d3b414_1744678.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142632_298def4a_1744678.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0924/142638_ad76d292_1744678.png "屏幕截图.png")



## 开源交流

- [我的CSDN博客-mingnianshimanian](https://blog.csdn.net/woshiwangdaye)
- [我的简书](https://www.jianshu.com/u/dfef29a4a6ff)
- [我的邮箱](wangpanfigo@126.com)
- 我的QQ：509961766
- 微信公众号
![](https://images.gitee.com/uploads/images/2019/0818/180127_8df1e0cf_1744678.png)

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
