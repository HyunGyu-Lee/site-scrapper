<template>
  <v-card>
    <v-list-item three-line>
      <v-list-item-content class="align-self-start">
        <v-list-item-title class="headline mb-2" v-text="scrap.title"></v-list-item-title>
        <v-list-item-subtitle v-text="scrap.description"></v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-avatar size="125" tile>
        <v-img :src="scrap.image"></v-img>
      </v-list-item-avatar>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
    props: {
        scrap: {
            type: Object,
            required: true
        }
    }
};
</script>

<style>
</style>