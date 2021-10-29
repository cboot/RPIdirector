<template>
    <div>
        <h1> organization details for group {{ details.name }} </h1>
    </div>
</template>

<script>
export default {
  data: function () {
    return {
      organizationId: "",
      details: "",
    };
  },
  created() {
      this.organizationId = this.$route.params.organizationId;
  },
  mounted() {
    this.refresh();
  },
  methods: {
    refresh: function () {
      const requestOptions = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": this.$root.authString,
        },
      };

      fetch(
        "http://localhost:8080/api/private/organizations/" +
          this.organizationId,
        requestOptions
      )
        .then((response) => {
          if (response.status != 200) {
            alert("Error!");
          } else {
            return response.json();
          }
        })
        .then((data) => {
          this.details = data.organization;
        })
        .catch((error) => alert("Error: " + error));
    },
  },
};
</script>