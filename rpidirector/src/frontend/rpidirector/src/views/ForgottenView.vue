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
            
            <b-button variant="primary" v-if="codeSent" v-on:click="postCode"> Confirm code </b-button>
            <b-button variant="primary" v-else v-on:click="requestCode"> Reset password</b-button>
            <b-button to="/"> Cancel </b-button>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import TheWelcomeMessage from "../components/TheWelcomeMessage.vue";
export default {
  components: { TheWelcomeMessage },
  data: function () {
    return {
      email: "",
      codeSent: false,
      code: "",
      timeout:""
    };
  },
  methods: {
    requestCode: function() {

      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      };

      fetch("http://localhost:8080/api/public/users/requestRecoverPasswordCode/"+this.email, requestOptions)
      .then((response) => {
        if (response.ok) {
          this.codeSent = true;
          this.timeout = setTimeout(function() {
            this.codeSent = false;
          }.bind(this), 120000);
        } else {
          throw new Error("Failed to request code");
        }
      })
      .catch((error) => alert("Error: " + error));
    },
    postCode: function() {

      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      };

      fetch("http://localhost:8080/api/public/users/postRecoverPasswordCode/"+this.email+"/"+this.code, requestOptions)
      .then((response) => {
        if (response.ok) {
          return response.json()
        } else {
          throw new Error("Failed to post code");
        }
      })
      .then((data) => {
          this.$root.setLoggedIn(data.user);
      })
      .catch((error) => alert("Error: " + error));
    }
  },
};
</script>