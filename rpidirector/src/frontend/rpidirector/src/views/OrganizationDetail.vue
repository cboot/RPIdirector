<template>
  <div>
    <h1>Details for organization {{ details.name }}</h1>
    <b-row>
      <b-col> Role: {{ details.role }} </b-col>
      <b-col> Since: {{ details.since }} </b-col>
    </b-row>
    <b-row>
      <b-col> Groups: {{ details.groups }} </b-col>
      <b-col cols="1">
        <my-add-button v-b-modal.modal-1 />
        <b-modal id="modal-1" title="Add a new group">
          <p class="my-4">Hello from modal!</p>
        </b-modal>
      </b-col>
    </b-row>
    <b-row>
      <b-col> Computers: {{ details.computers }} </b-col>
      <b-col> </b-col>
    </b-row>
  </div>
</template>

<script>
import OrganizationService from "../services/organizations";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import MyAddButton from '../components/MyAddButton.vue';
library.add(faPlus);

export default {
  components: { MyAddButton },
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
      OrganizationService.get(this.organizationId)
        .then((response) => {
          this.details = response.data.organization;
        })
        .catch((error) => alert("Error: " + error));
    },
  },
};
</script>