<template>
  <div>
    You have access to {{ organizations.length }} organizations
    <li v-for="item in organizations" :key="item.id">
      <organization-list-item
        :propOrganizationInfo="item"
        v-on:refreshFromChild="refresh"
      />
    </li>
  </div>
</template>

<script>
import OrganizationService from "../services/organizations";
import OrganizationListItem from "./OrganizationListItem.vue";
export default {
  components: { OrganizationListItem },
  name: "OrganizationsList",
  data: function () {
    return { organizations: [] };
  },
  methods: {
    refresh: function () {
      OrganizationService.getAll()
        .then((response) => {
          this.organizations = response.data.organizationsList;
        })
        .catch((error) => {
          alert("theres an error:" + error);
        });
    },
  },
  created: function () {
    this.refresh();
  },
};
</script>