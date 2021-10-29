import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue  } from 'bootstrap-vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(BootstrapVue);

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  data: function() {
    return {
      user : "",
      authString : "",
    }
  },
  methods: {
    setLoggedIn(user) {
      this.user = user;
      this.authString = "Bearer " + btoa(user.id + ":" + user.token);
      this.$router.push("/userPanel");
    },
    logout() {
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization" : this.authString
        },
      };

      fetch("http://localhost:8080/api/private/users/logout", requestOptions)
      .then((response) => {
        if (response.status != 200) {
          alert("Error!");
        }
        this.authString = "";
        this.user = "";
        this.$router.push("/");
      }).catch((error) => alert("Error: " + error));
    },
    checkForLogin() {
      if (this.user === "") {
        router.push("/");
      }
    }
  }
}).$mount('#app')
