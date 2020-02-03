import axios from 'axios'

export default axios.create({
  // baseURL: 아직 설정 안되어있음...
  baseURL: 'http://13.125.153.118:8080/api',
  headers: {
    'Content-type': 'application/json; charset=UTF-8;'
  }
})
