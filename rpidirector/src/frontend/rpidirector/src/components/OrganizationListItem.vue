<template>
  <div>
    <b-col class="panel">
      <router-link
        :to="{ path: '/organizations/' + this.propOrganizationInfo.id }"
        >Details</router-link
      >
      <my-confirm-action :modalTitle="'Delete organization'" :modalBody="'This is the body'" :icon="'trash'" :id="'modal-delete-organization-'+ propOrganizationInfo.id" />
      <font-awesome-icon
        :icon="['fas', 'trash']"
        v-on:click="deleteOrganization"
        v-if="propOrganizationInfo.role == 'OWNER'"
      />
      <font-awesome-icon
        :icon="['fas', 'doorOpen']"
        v-on:click="leaveOrganization"
        v-if="propOrganizationInfo.role != 'OWNER'"
      />

      <ul>
        <font-awesome-icon :icon="['fas', 'sitemap']"></font-awesome-icon>
        <li>{{ propOrganizationInfo.name }}</li>
        <li>{{ propOrganizationInfo.role }}</li>
        <li>{{ propOrganizationInfo.since }}</li>
        <li>{{ propOrganizationInfo.groups }}</li>
        <li>{{ propOrganizationInfo.computers }}</li>
      </ul>
    </b-col>
  </div>
</template>

<script>
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faSitemap,
  faTrash,
  faDoorOpen,
  faCogs,
} from "@fortawesome/free-solid-svg-icons";
import OrganizationService from "../services/organizations";
import MyConfirmAction from './MyConfirmAction.vue';
library.add(faSitemap, faTrash, faDoorOpen, faCogs);

export default {
  components: { MyConfirmAction },
  name: "OrganizationsListItem",
  props: ["propOrganizationInfo"],
  methods: {
    deleteOrganization: function () {
      OrganizationService.delete(this.propOrganizationInfo.id)
        .then(() => {
          this.$emit("refreshFromChild", null);
        })
        .catch((error) => alert("Error: " + error));
    },
    leaveOrganization: function () {
      OrganizationService.leave(this.propOrganizationInfo.id)
        .then(() => {
          this.$emit("refreshFromChild", null);
        })
        .catch((error) => alert("Error: " + error));
    },
    getDetailLink() {
      return "/organizations/" + this.propOrganizationInfo.id;
    },
  },
};
</script>