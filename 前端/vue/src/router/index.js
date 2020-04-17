import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }

]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [

  {
    path: '/manager',
    component: Layout,
    redirect: '/manager/',
    name: '',
    meta: {title: '个人中心', icon: 'table'},
    children: [
      
      {
        path: '',
        name: '缺勤记录',
        component: _import('manager/manager'),
        meta: {title: '缺勤记录', icon: 'example'},
        menu: 'manager'
      },
      {
        path: 'absent',
        name: '缺勤登记',
        component: _import('manager/absent'),
        meta: {title: '缺勤登记', icon: 'form'},
        menu: 'manager'
      }
    ]
  },
  {
    path: '/member',
    component: Layout,
    redirect: '/member/',
    name: '',
    meta: {title: '成员信息', icon: 'table'},
    children: [
      {
        path: 'member',
        name: '项目信息',
        component: _import('member/member'),
        meta: {title: '项目信息', icon: 'example'},
        menu: 'member'
      },
      {
        path: 'member',
        name: '任务信息',
        component: _import('member/member'),
        meta: {title: '任务信息', icon: 'example'},
        menu: 'member'
      },
      
      {
        path: 'memberAbsent',
        name: '缺勤信息',
        component: _import('member/memberAbsent'),
        meta: {title: '缺勤信息', icon: 'example'},
        menu: 'member'
      },
    ]
  },
  // {
  //   path: '/makeproject',
  //   component: Layout,
  //   redirect: '/makeproject/',
  //   name: '立项管理',
  //   meta: {title: '立项管理', icon: 'table'},
  //   children: [
  //     {
  //       // path: 'createproject', name: '创建项目', component: _import('makeproject/createproject'),
  //       //  meta: {title: '项目列表', icon: 'tree'}, menu: 'makeproject'
  //     }
  //     // {
  //     //   path: 'log', name: '日志列表', component: _import('user/log'), meta: {title: '日志列表', icon: 'eye'}, menu: 'log'
  //     // }
  //   ]

  // },
  
  // {
  //   path: '/programmer',
  //   component: Layout,
  //   redirect: '/programmer/',
  //   name: '',
  //   meta: {title: '开发管理', icon: 'table'},
  //   children: [
  //     // {
  //     //   path: 'mypjm', name: '我的项目', component: _import('programmer/mypjm'),
  //     //  meta: {title: '我的项目', icon: 'tree'}, menu: 'programmer'
  //     // },
  //     // {
  //     //   path: 'mypjm', name: '权限管理', component: _import('user/role'),
  //     //   meta: {title: '权限管理', icon: 'tree'},
  //     //   menu: 'programmer'
  //     // },
  //     // {
  //     //   path: 'log', name: '日志列表', component: _import('user/log'), meta: {title: '日志列表', icon: 'eye'}, menu: 'log'
  //     // }
  //   ]

  // },
  // {
  //   path: '/test',
  //   component: Layout,
  //   redirect: '/test/',
  //   name: '',
  //   meta: {title: '测试管理', icon: 'table'},
  //   children: [
  //     // {
  //     //   path: 'testtask', name: '测试任务', component: _import('test/test'), 
  //     //   meta: {title: '测试任务', icon: 'tree'}, menu: 'test'
  //     // },
  //     // {
  //     //   path: 'taskmanger',
  //     //   name: '任务管理',
  //     //   component: _import('user/taskmanger'),
  //     //   meta: {title: '任务管理', icon: 'password'},
  //     //   menu: 'taskmanger'
      
  //     // },
      
  //   ]

  // },



  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '系统管理', icon: 'table'},
    children: [
      {
        path: 'user', name: '用户列表', component: _import('user/user'), meta: {title: '用户列表', icon: 'user'}, menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('user/role'),
        meta: {title: '权限管理', icon: 'password'},
        menu: 'role'
      },
      {
        path: 'log', name: '日志列表', component: _import('user/log'), meta: {title: '日志列表', icon: 'eye'}, menu: 'log'
      }
    ]

  },
  
  {path: '*', redirect: '/404', hidden: true}
]
