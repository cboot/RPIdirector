<template>
  <button>
    <font-awesome-icon :icon="['fas', icon ]" size="2x"  v-b-modal="id"/>
    <b-modal :id="id" :title="modalTitle">
          <p class="my-4">{{ modalBody }}</p>
        </b-modal>
  </button>
</template>

<script>
export default {
  name: "MyConfirmAction",
  props: ["icon", "callback", "modalTitle", "modalBody", "id"],
  methods: {
    clicked: function () {
      this.$emit("clicked", this.action);
    },
  },
};
</script>