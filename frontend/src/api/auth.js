import axios from 'axios'

export default {
    login(loginId, loginPassword) {
        return axios.post('/api/auth/signin', {loginId, loginPassword})
    }
}