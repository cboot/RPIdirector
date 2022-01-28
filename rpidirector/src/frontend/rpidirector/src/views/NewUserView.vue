<template>
  <div>
    <the-welcome-message />
    <b-row class="justify-content-center">
      <b-col cols="6" class="panel">
        <h3>Create an account, its free!</h3>
        <b-form-group
          id="email-group"
          label="Email:"
          label-for="email"
          valid-feedback="Thank you!"
          label-cols-md="2"
        >
          <b-form-input id="email" v-model="email" />
        </b-form-group>
        <b-form-group
          id="password-group"
          label="Password:"
          label-for="password"
          valid-feedback="Thank you!"
          label-cols-md="2"
        >
          <b-form-input type="password" id="password" v-model="password" />

        </b-form-group>
        <b-form-group
          id="name-group"
          label="Your name:"
          label-for="name"
          valid-feedback="Thank you!"
          label-cols-md="2"
        >
          <b-form-input id="name" v-model="name" />
        </b-form-group>
        <b-row class="justify-content-center">
          <b-col class="d-flex justify-content-between">
            <b-button variant="primary" v-on:click="register">Sign up</b-button>
            <b-button to="/">Cancel</b-button>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import TheWelcomeMessage from "../components/TheWelcomeMessage.vue";
import UserService from '../services/users';

export default {
  components: { TheWelcomeMessage },
  data: function () {
    return {
      email: "",
      password: "",
      name: "",
    };
  },
  methods:{
    register: function() {
      UserService.register(this.email, this.password, this.name);
    },
  },
};
</script>