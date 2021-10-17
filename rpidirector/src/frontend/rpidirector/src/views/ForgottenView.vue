<template>
  <div class="row no-gutters d-flex justify-content-center">
    <h1 class="center">Welcome to RPIDirector</h1>
    <div class="w-100 center">
      <img src="@/assets/img/logo.png" class="w-25" />
    </div>
    <div class="mt-5 pt-2 pb-2 panel w-50 h-50">
      Forgotten password
    </div>
  </div>
</template>

<script>

export default {
  name: "ForgottenView",
  data: function () {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
   
  },
};
</script>