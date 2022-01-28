<template>
  <div>
    <b-row>
      <h1>User panel</h1>
      <b-button v-on:click="this.$root.logout">Logout</b-button>
    </b-row>
    <b-row>
      <organizations-list ref="organizationsList" />
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
import OrganizationsList from "../components/OrganizationsList.vue";
import OrganizationService from "../services/organizations";

export default {
  components: { OrganizationsList },
  data: function () {
    return {
      organizationName: "",
    };
  },
  methods: {
    createNewOrganization: function () {
      OrganizationService.create(this.organizationName)
        .then(() => {
          this.$refs.organizationsList.refresh();
        })
        .catch((error) => {
          alert("Error: " + error);
        });
    },
  },
};
</script>
