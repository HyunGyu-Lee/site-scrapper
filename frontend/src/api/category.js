import axios from 'axios'

const API_PATH = '/api/categories';

export default {
  list(userId) {
    return axios.get(`${API_PATH}?userId=${userId}`);
  },
  create(request) {
    return axios.post(`${API_PATH}`, request)
  },
  edit(request) {
    return axios.put(`${API_PATH}/${request.id}`, { name: request.name })
  },
  delete(id) {
    return axios.delete(`${API_PATH}/${id}`);
  }
}