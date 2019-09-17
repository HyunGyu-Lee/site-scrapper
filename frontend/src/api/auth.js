import axios from 'axios'

export default {
    login(id, password) {
        return axios.post('/api/auth/sign-in', {id, password})
    }
}