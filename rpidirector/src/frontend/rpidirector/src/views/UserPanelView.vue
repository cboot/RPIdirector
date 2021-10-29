<template>
  <div>
    <b-row>
      <h1>User panel</h1>
      <b-button v-on:click="this.$root.logout">Logout</b-button>
    </b-row>
    <b-row>
      <organizations-list ref="organizationsList"/>
    </b-row>
    <b-row>
      <b-form-group
        id="code-group"
        description="New organizations name"
        label="New organization name:"
        label-for="code"
        label-cols-md="2"
      >
        <input
          id="organizationName"
          type="text"
          class="form-control"
          v-model="organizationName"
        />
      </b-form-group>
      <b-button v-on:click="createNewOrganization">Create new</b-button>
    </b-row>
  </div>
</template>

<script>
import OrganizationsList from '../components/OrganizationsList.vue';

export default {
  components: { OrganizationsList },
  data: function () {
    return {
      organizationName: "",
    };
  },
  methods: {
    createNewOrganization: function () {
      if (this.organizationName == "") {
        return;
      }
      const payload = {
          name : this.organizationName
      }
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": this.$root.authString,
          
        },
        body: JSON.stringify(payload),
      };

      fetch("http://localhost:8080/api/private/organizations/", requestOptions)
        .then((response) => {
          if (response.status != 200) {
            alert("Error!");
          } else {
            return response.json();
          }
        })
        .then(() => {
          this.$refs.organizationsList.refresh();
        })
        .catch((error) => alert("Error: " + error));
      alert(
        "Creating new org with name " +
          this.organizationName +
          " and user owner id: " +
          this.$root.user.id
      );
    },
  },
};
</script>
