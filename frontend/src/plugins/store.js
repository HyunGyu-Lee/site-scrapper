import Vue from 'vue'
import Vuex from 'vuex'
import Auth from '@/api/auth'
import Const from '@/common/constants'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [createPersistedState()],
    state: {
        accessToken: '',
        authenticated: false
    },
    mutations: {
        [Const.MUTATIONS.USER_LOGIN] : (state, accessToken) => {
            state.accessToken = accessToken;
            state.authenticated = true;
        },
        [Const.MUTATIONS.USER_LOGOUT] : (state) => {
            state.accessToken = '';
            state.authenticated = false;
        }
    },
    actions: {
        LOGIN({ commit }, { id, password }) {
            return new Promise(function (resolve, reject) {
                Auth.login(id, password).then(response => {
                    commit(Const.MUTATIONS.USER_LOGIN, response.data.body);
                    resolve();
                }).catch(e => {
                    reject(e);
                });
            });
        },
        LOGOUT({ commit }) {
            commit(Const.MUTATIONS.USER_LOGOUT)
        }
    },
    getters: {
        accessToken(state) {
            return state.accessToken;
        },
        isAuthorized(state) {
            return state.authenticated;
        }
    }
})
