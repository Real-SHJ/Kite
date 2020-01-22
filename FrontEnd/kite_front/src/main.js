<<<<<<< HEAD
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'
=======
>>>>>>> a5e070104e235c060f0d792e4e28fb5758f344bc

import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

Vue.config.productionTip = false;
Vue.use(Vuetify)

new Vue({
  router,
  store,
<<<<<<< HEAD
  vuetify,
  render: h => h(App)
}).$mount('#app')
=======
  render: (h) => h(App),
}).$mount('#app');
>>>>>>> a5e070104e235c060f0d792e4e28fb5758f344bc
