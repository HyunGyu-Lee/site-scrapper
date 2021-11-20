import axios from 'axios';
import store from '@/plugins/store';
import router from '@/plugins/router';
import applib from '@/common/app';

axios.interceptors.request.use((config) => {
    let accessToken = store.getters.accessToken;
    if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
});

axios.interceptors.response.use((response) => { return response; }, (error) => {
  if (error.response.status == 403) {
    applib.toast('세션이 유효하지 않습니다.');
    store.dispatch("LOGOUT");
    router.push('/login');
    applib.finishLoading();
  }
  return Promise.reject(error);
});

export default {
    login(loginId, loginPassword) {
        return axios.post('/api/users/signin', {loginId, loginPassword})
    }
}