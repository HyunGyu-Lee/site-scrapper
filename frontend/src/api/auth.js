import axios from 'axios';
import store from '@/plugins/store';
import router from '@/plugins/router';

axios.interceptors.request.use((config) => {
    let accessToken = store.getters.accessToken;
    if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
});

axios.interceptors.response.use((response) => { return response; }, (error) => {
  store.dispatch("LOGOUT");
  router.push('/login')
  return Promise.reject(error);
})

export default {
    login(loginId, loginPassword) {
        return axios.post('/api/users/signin', {loginId, loginPassword})
    }
}