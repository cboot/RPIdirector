<template>
  <div class="row no-gutters d-flex justify-content-center">
    <h1 class="center">Welcome to RPIDirector</h1>
    <div class="w-100 center">
      <img src="@/assets/img/logo.png" class="w-25" />
    </div>
    <div class="mt-5 pt-2 pb-2 panel w-50 h-50">
      <div class="form-group">
        <label for="username">Username:</label>
        <input
          id="username"
          type="text"
          class="form-control"
          v-model="username"
        />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input
          id="password"
          type="password"
          class="form-control"
          v-model="password"
        />
      </div>

      <div class="d-flex justify-content-around mt-2">
        <button type="button" class="btn btn-primary" v-on:click="login">
          Log in
        </button>
        <router-link to="/forgotten-password"><span class="italic">Forgot password?</span></router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data: function () {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login: function () {
      const authorizationHeader = "Basic " + btoa(username + ":" + password);
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: authorizationHeader,
        },
      };
      fetch("http://localhost:8000/api/login", requestOptions).then((response) =>
        alert(response)
      );
    },
  },
};
</script>