<template>
  <b-container>
    <theWelcomeMessage />
    <b-row class="justify-content-center">
      <b-col cols="6" class="panel">
        <b-form-group
          id="email-group"
          label="Email:"
          label-for="email"
          label-cols-md="2"
        >
          <b-form-input v-model="email" />
        </b-form-group>
        <b-form-group
          id="password-group"
          label="Password:"
          label-for="password"
          label-cols-md="2"
        >
          <b-form-input
            v-model="password"
            type="password"
          />
        </b-form-group>

        <div class="d-flex justify-content-around mt-2">
          <b-button variant="primary" v-on:click="login">Log in</b-button>
          <b-link to="/forgotten-password"
            ><span class="italic">Forgot password?</span></b-link
          >
        </div>
      </b-col>
    </b-row>
    <b-row class="justify-content-center"> or </b-row>
    <b-row class="justify-content-center">
      <b-col cols="6" class="panel center pt-3 pb-4"> 
        <b-button variant="primary" to="/new-user"> Create a new account </b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import TheWelcomeMessage from "../components/TheWelcomeMessage.vue"

export default {
  components: { TheWelcomeMessage },
  data: function () {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    login: function() {
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization" : "Basic " + btoa(this.email + ":" + this.password)
        },
      };

      fetch("http://localhost:8080/api/public/users/login", requestOptions)
      .then((response) => {
        if (response.status != 200) {
          alert("Error!");
        } else {
          return response.json();
        }
      })
      .then((data) => {
          this.$root.setLoggedIn(data.user);
      }).catch((error) => alert("Error: " + error));
    }
  },
}
</script>