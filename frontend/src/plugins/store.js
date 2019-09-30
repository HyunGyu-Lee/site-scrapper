import Vue from 'vue'
import Vuex from 'vuex'
import Auth from '@/api/auth'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [createPersistedState()],
    state: {
        accessToken: '',
        authenticated: false
    },
    mutations: {
        USER_LOGINED(state, { accessToken }) {
            state.accessToken = accessToken;
            state.authenticated = true;
        },
        USER_LOGOUT(state) {
            state.accessToken = '';
            state.authenticated = false;
        }
    },
    actions: {
        LOGIN({ commit }, { id, password }) {
            return new Promise(function (resolve, reject) {
                Auth.login(id, password).then(response => {
                    commit('USER_LOGINED', response.data.body);
                    Auth.authorize(response.data.body);
                    resolve();
                }).catch(e => {
                    reject(e);
                });
            });
        },
        LOGOUT({ commit }) {
            commit('USER_LOGOUT')
            Auth.unauthorize();
        }
    },
    getters: {
        isAuthorized(state) {
            return state.authenticated;
        }
    }
})
