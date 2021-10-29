<template>
  <div>
    <b-col class="panel">
      <router-link :to="{ path: '/organizations/'+this.propOrganizationInfo.id}">aaaaaaaa</router-link>
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
  faCogs
} from "@fortawesome/free-solid-svg-icons";
library.add(faSitemap, faTrash, faDoorOpen, faCogs);

export default {
  name: "OrganizationsListItem",
  props: ["propOrganizationInfo"],
  methods: {
    getOrganizationInfo: function () {
      const requestOptions = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.$root.authString,
        },
      };

      fetch(
        "http://localhost:8080/api/private/organizations/" +
          this.propOrganizationInfo.id,
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
          console.log(data);
        })
        .catch((error) => alert("Error: " + error));
    },
    deleteOrganization: function () {
      const requestOptions = {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.$root.authString,
        },
      };

      fetch(
        "http://localhost:8080/api/private/organizations/" +
          this.propOrganizationInfo.id,
        requestOptions
      )
        .then((response) => {
          if (response.status != 200) {
            alert("Error!");
          } else {
            alert("deleting");
            this.$emit("refreshFromChild", null);
          }
        })
        .catch((error) => alert("Error: " + error));
    },
    leaveOrganization: function () {
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.$root.authString,
        },
      };

      fetch(
        "http://localhost:8080/api/private/organizations/" +
          this.propOrganizationInfo.id +
          "/leave",
        requestOptions
      )
        .then((response) => {
          if (response.status != 200) {
            alert("Error!");
          } else {
            alert("leaving");
            this.$emit("refreshFromChild", null);
          }
        })
        .catch((error) => alert("Error: " + error));
    },
    getDetailLink() {
      return "/organizations/" + this.propOrganizationInfo.id;
    }
  },
};
</script>