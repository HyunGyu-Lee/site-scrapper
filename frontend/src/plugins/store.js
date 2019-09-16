import Vue from 'vue'
import Vuex from 'vuex'
import Auth from '@/api/auth';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: '',
    authenticated: false
  }, 
  mutations: { 
    LOGIN (state, {accessToken}) {
      state.accessToken = accessToken;
      state.authenticated = true;
    }
  },
  actions: {
    LOGIN ({commit}, {id, password}) {
      return Auth.login(id, password).then((response) => {
        commit('LOGIN', response.data.body);
      })      
    }
  }
})
