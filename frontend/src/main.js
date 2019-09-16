import Vue from 'vue'
import App from './App.vue'
import router from './plugins/router'
import store from './plugins/store'
import vuetify from './plugins/vuetify';
import moment from 'moment';
import VueMomentJS from 'vue-momentjs';

Vue.config.productionTip = false

Vue.use(VueMomentJS, moment);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
