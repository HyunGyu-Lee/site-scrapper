import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import Auth from '@/api/auth'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: '',
    authenticated: false
  }, 
  mutations: { 
    USER_LOGINED (state, {accessToken}) {
      state.accessToken = accessToken;
      state.authenticated = true;
    }
  },
  actions: {
    LOGIN ({commit}, {id, password}) {
      return Auth.login(id, password)
        .then(response => {
          commit('USER_LOGINED', response.data.body)
          axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.body}`;
        })
        .catch(e => console.error(e));
    }
  },
  getters: {
    isAuthorized(state) {
      return state.authenticated;
    }
  }
})
