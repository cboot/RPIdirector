<template>
  <div>
    You have access to {{ organizations.length }} organizations
    <li v-for="item in organizations" :key="item.id">
      <organization-list-item :propOrganizationInfo="item" v-on:refreshFromChild="refresh" />
    </li>
  </div>
</template>

<script>
import OrganizationListItem from "./OrganizationListItem.vue";
export default {
  components: { OrganizationListItem },
  name: "OrganizationsList",
  data: function () {
    return { organizations: [] };
  },
  methods: {
    refresh: function () {
      const requestOptions = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.$root.authString,
        },
      };

      fetch("http://localhost:8080/api/private/organizations/", requestOptions)
        .then((response) => {
          if (response.status != 200) {
            alert("Error!");
          } else {
            return response.json();
          }
        })
        .then((data) => {
          this.organizations=[];
          for (var myItem in data.organizationsList) {
            this.organizations.push(data.organizationsList[myItem]);
          }
        })
        .catch((error) => alert("Error: " + error));
    },
  },
  created: function () {
    this.refresh();
  },
};
</script>