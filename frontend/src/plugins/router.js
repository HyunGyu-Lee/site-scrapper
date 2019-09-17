import Vue from 'vue'
import Router from 'vue-router'
import LoginView from '@/views/LoginView'
import store from '@/plugins/store'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    { path: '/login', component: LoginView },
    { path: '/scraps', component: () => import('@/views/ScrapView') },
    { path: '/settings', component: () => import('@/views/SettingView.vue') },
    { path: '*', redirect: '/scraps' }
  ]
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register'];
  const authRequired = !publicPages.includes(to.path);

  if (authRequired && !store.getters.isAuthorized) {
    return next('/login');
  }

  next();
})

export default router;