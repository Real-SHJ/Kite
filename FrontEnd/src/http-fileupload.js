import Axios from 'axios'

export default Axios.create({
  baseURL: 'http://localhost:8999/api/file',
  headers: {
    'Content-type': 'multipart/form-data;'
  }
})
