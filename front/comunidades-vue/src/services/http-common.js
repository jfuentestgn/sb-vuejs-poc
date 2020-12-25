import axios from "axios";
import store from '@/store';


var addAuthHeaderInterceptor = function(config) {
  console.log('addAuthHeaderInterceptor');
  const authHeader = store.state.authHeader;
  config.headers['Authorization'] = authHeader;
  return config;
};
/*
var unauthorizedInterceptor = function() {

};*/

var axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json"
  }
});

axiosInstance.interceptors.request.use(addAuthHeaderInterceptor);

export default axiosInstance;