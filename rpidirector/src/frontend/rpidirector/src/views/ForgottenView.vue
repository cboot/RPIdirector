<template>
  <div>
    <the-welcome-message />
    <b-row class="justify-content-center">
      <b-col cols="6" class="panel">
        <b-row class="align-items-center">
          <b-col cols="12">
            <b-form-group
              id="email-group"
              description="The email you registered with"
              label="Email:"
              label-for="email"
              label-cols-md="2"
              v-if="!codeSent"
            >
              <input
                id="email"
                type="text"
                class="form-control"
                v-model="email"
              />
            </b-form-group>
            <b-form-group
              id="code-group"
              description="A code has been sent to your email"
              label="Code:"
              label-for="code"
              label-cols-md="2"
              v-else
            >
              <input
                id="code"
                type="text"
                class="form-control"
                v-model="code"
              />
            </b-form-group>
          </b-col>
          <b-col cols="12" class="d-flex justify-content-between">
            <b-button variant="primary" v-if="codeSent" v-on:click="postCode">
              Confirm code
            </b-button>
            <b-button variant="primary" v-else v-on:click="requestCode">
              Reset password</b-button
            >
            <b-button to="/"> Cancel </b-button>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import TheWelcomeMessage from "../components/TheWelcomeMessage.vue";
import UserService from "../services/users";

export default {
  components: { TheWelcomeMessage },
  data: function () {
    return {
      email: "",
      codeSent: false,
      code: "",
      timeout: "",
    };
  },
  methods: {
    requestCode: function () {
      UserService.requestCode(this.email)
      .then( () => {
        this.codeSent = true;
        this.timeout = setTimeout(
          function () {
            this.codeSent = false;
          }.bind(this),
          120000
        );
      })
    },
    postCode: function () {
      UserService.postCode(this.email, this.code);
    },
  },
};
</script>