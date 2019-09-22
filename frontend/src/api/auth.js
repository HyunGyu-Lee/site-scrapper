import axios from 'axios'

export default {
    authorize(accessToekn) {
        axios.defaults.headers.common['X-AUTH-TOKEN'] = accessToekn;
    },
    unauthorize() {
        axios.defaults.headers.common['X-AUTH-TOKEN'] = '';
    },
    login(loginId, loginPassword) {
        return axios.post('/api/auth/signin', {loginId, loginPassword})
    }
}