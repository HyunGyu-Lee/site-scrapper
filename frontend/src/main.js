import Vue from 'vue'
import moment from 'moment';
import 'moment/locale/ko'
import VueMomentJS from 'vue-momentjs';


Vue.config.productionTip = false
Vue.use(VueMomentJS, moment);

import App from './App.vue'
import router from '@/plugins/router'
import store from '@/plugins/store'
import vuetify from '@/plugins/vuetify';
import applib from '@/common/app'

Vue.prototype.$app = applib;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
