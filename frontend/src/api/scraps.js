import axios from 'axios'

const API_PATH = '/api/scraps';

export default {
    list(userId) {
        return axios.get(`${API_PATH}?userId=${userId}`);
    },
    create(scrapRequest) {
        return axios.post('/api/scraps', scrapRequest);
    }
}