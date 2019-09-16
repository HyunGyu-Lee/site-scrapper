import axios from 'axios'

export default {
    list() {
        return axios.get('/api/scrap');
    },
    create(scrapRequest) {
        return axios.post('/api/scrap', scrapRequest);
    }
}