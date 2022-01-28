import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue } from 'bootstrap-vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import UserService from './services/users.js'
import ApiService from './services/api'
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(BootstrapVue);

Vue.config.productionTip = false

window.vue = new Vue({
  render: h => h(App),
  router,
  data: function () {
    return {
      user: "",
      authString: "",
    }
  },
  methods: {
    setLoggedIn(user) {
      this.user = user;
      this.authString = "Bearer " + btoa(user.id + ":" + user.token);
      this.$router.push("/userPanel");
      ApiService.setHeader();
    },
    logout() {
      UserService.logout()
      .then(() => {
        this.authString = "";
        this.user = "";
        this.$router.push("/");
      });
    }
  },
  checkForLogin() {
    if (this.user === "") {
      router.push("/");
    }
  }
}).$mount('#app')