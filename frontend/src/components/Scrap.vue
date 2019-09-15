<template>
  <v-card>
    <v-img class="white--text" height="200px" :src="scrap.image">
      <v-card-title class="align-end fill-height">
      <span class="title text-truncate">
        <strong>{{scrap.title}}</strong>
      </span>
      </v-card-title>
    </v-img>
    <!-- <v-card-title>
      <span class="subtitle-1 text-truncate">
        <strong>{{scrap.title}}</strong>
      </span>
    </v-card-title> -->
    <v-divider></v-divider>
    <v-card-text v-text="scrap.description"></v-card-text>
    <v-card-actions>
      <v-btn text :link="true" :href="scrap.url" target="_blank">READ</v-btn>
    </v-card-actions>
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