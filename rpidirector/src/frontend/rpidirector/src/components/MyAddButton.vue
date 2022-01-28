<template>
  <button>
    <font-awesome-icon :icon="['fas', 'plus']" size="4x" v-on:click="clicked" />
  </button>
</template>

<script>
export default {
  name: "MyAddButton",
  props: ["action"],
  methods: {
    clicked: function () {
      this.$emit("clicked", this.action);
    },
  },
};
</script>