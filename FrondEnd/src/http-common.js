import axios from 'axios'

export default axios.create({
  // baseURL: 아직 설정 안되어있음...
  baseURL: 'http://localhost:8090/api',
  headers: {
    'Content-type': 'application/json'
  }
})
