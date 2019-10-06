import axios from 'axios'
import store from '@/plugins/store'

axios.interceptors.request.use((config) => {
    let accessToken = store.getters.accessToken;
    if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
});

export default {
    login(loginId, loginPassword) {
        return axios.post('/api/auth/signin', {loginId, loginPassword})
    }
}