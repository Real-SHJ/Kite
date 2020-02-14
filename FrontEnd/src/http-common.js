import axios from 'axios'

export default axios.create({
  // baseURL: 'http://13.125.153.118:8080/api',
  // baseURL: 'http://localhost:8080/api',
  // baseURL: 'http://192.168.100.72:8080/api',
  baseURL: 'http://192.168.100.75:8080/api',
  headers: {
    'Content-type': 'application/json; charset=UTF-8;'
  }
})
