import axios from 'axios'

export default {
    login(id, password) {
        return axios.post('TODO_AUTH_URL', {id, password})
    }
}