<template>
  <v-card>
    <v-img class="white--text" height="200px" :src="scrap.image">
      <v-card-title class="align-end fill-height">
        <span class="title text-truncate">
          <strong>{{scrap.title}}</strong>
        </span>
      </v-card-title>
    </v-img>
    <v-divider></v-divider>
    <v-card-text>
      <span v-text="scrap.description"></span>
      <v-btn icon @click="showDetails = !showDetails">
        <v-icon>{{showDetails ? 'mdi-chevron-up' : 'mdi-chevron-down'}}</v-icon>
      </v-btn>
    </v-card-text>
    <v-expand-transition>
      <div v-show="showDetails">
        <v-card-text>
          <span class="caption">
            <strong>SOURCE URL</strong>
          </span>
          <br />
          <span v-text="scrap.url"></span>
          <br />
          <br />
          <span class="caption">
            <strong>SCRAPED AT</strong>
          </span>
          <br />
          <span class="overline" v-text="dateFormat(scrap.createdAt)"></span>
        </v-card-text>
      </div>
    </v-expand-transition>
    <v-divider></v-divider>
    <v-card-actions>
      <div class="flex-grow-1"></div>
      <v-btn icon :link="true" :href="scrap.url" target="_blank">
        <v-icon>mdi-share-variant</v-icon>
      </v-btn>
      <v-btn icon :link="true" :href="scrap.url" target="_blank">
        <v-icon>mdi-launch</v-icon>
      </v-btn>
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
  },
  data: () => ({
    showDetails: false
  }),
  methods: {
    dateFormat: function(dateString) {
      return this.$moment(dateString).format("YYYY-MM-DD HH:mm:ss");
    }
  }
};
</script>

<style>
</style>