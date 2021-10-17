<template>
  <div id="app">
        <router-view />
  </div>
</template>

<script>
export default {
  components: {},
  name: "App",
  head: {
    script: [
      { src: "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" },
      { src: "https://code.jquery.com/jquery-3.6.0.min.js" },
      { src: "https://kit.fontawesome.com/1a96e512b3.js" },
    ],
  },
};
</script>

<style>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css";
@import "assets/css/style.css";
</style>

