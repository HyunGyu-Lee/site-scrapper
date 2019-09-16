import Vue from 'vue'
import Router from 'vue-router'
import LoginView from '@/views/LoginView'

Vue.use(Router)

const requireAuth = (to, from, next) => {
  next({
    path: '/login',
    query: {redirectUrl: to.fullPath}
  })
}

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      name: 'login-view',
      component: LoginView
    },
    {
      path: '/scraps',
      name: 'scrap-view',
      component: () => import('@/views/ScrapView'),
      beforeEnter: requireAuth
    },
    {
      path: '/settings',
      name: 'setting-view',
      component: () => import(/* webpackChunkName: "about" */ '@/views/SettingView.vue'),
      beforeEnter: requireAuth
    }
  ]
})
