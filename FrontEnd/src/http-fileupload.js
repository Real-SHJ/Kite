import Axios from 'axios'

export default Axios.create({
  baseURL: 'http://13.125.153.118:8999/api/file',
  headers: {
    'Content-type': 'multipart/form-data;'
  }
})
