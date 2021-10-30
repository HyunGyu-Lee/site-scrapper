<template>
  <div>
    <v-card v-if="scrapViewType == 'card'">
      <v-img v-if="presentImage(scrap.image)" class="white--text" height="220" :src="scrap.image"></v-img>
      <v-divider></v-divider>
      <v-subheader>
          <span class="title text-truncate">
            <strong>{{scrap.title}}</strong>
          </span>
      </v-subheader>
      <v-card-text>
        <span v-text="description"></span>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-row dense class="px-1" align="center" justify="end">
          <v-col cols="4">
            <small>{{scrapedAt}}</small>
          </v-col>
          <v-spacer />
          <v-col cols="2">
            <v-btn icon @click.stop="deleteScrap(scrap.id)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </v-col>
          <v-col cols="2">
            <v-btn icon :link="true" :href="scrap.url" target="_blank">
              <v-icon>mdi-launch</v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
    <v-list-item v-if="scrapViewType == 'list'">
      <v-list-item-avatar height="100" width="100">
        <v-img :src="presentImage(scrap.image)"></v-img>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title v-text="scrap.title" />
        <v-list-item-subtitle v-text="description" />
      </v-list-item-content>
      <v-list-item-action>
        <v-list-item-action-text v-text="scrapedAt" />
          <v-btn icon @click.stop="deleteScrap(scrap.id)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
          <v-btn icon :link="true" :href="scrap.url" target="_blank">
            <v-icon>mdi-launch</v-icon>
          </v-btn>
      </v-list-item-action>
    </v-list-item>
  </div>
</template>

<script>
export default {
  props: {
    scrap: {
      type: Object,
      required: true
    },
    descriptionLimit: {
      type: Number,
      default: 300
    },
    viewType: {
      type: String,
      default: 'card'
    }
  },
  data: () => ({
    showDetails: false    
  }),
  computed: {
    description() {
      if (this.getLength(this.scrap.description) > this.descriptionLimit) {
        return `${this.scrap.description.substring(0, this.descriptionLimit)}...`
      } else {
        return this.scrap.description
      }
    },
    scrapedAt() {
      return this.$moment(this.scrap.createdAt).fromNow()
    },
    scrapViewType:{
      get() {
        return this.viewType;
      },
      set(viewType) {
        return viewType;
      }
    }
  },
  methods: {
    dateFormat: function(dateString) {
      return this.$moment(dateString).format("YYYY-MM-DD HH:mm:ss");
    },
    presentImage: function () {
      return this.scrap.image
    },
    getLength(text) {
      var len = 0;
      for (var i = 0; i < text.length; i++) {
          if (escape(text.charAt(i)).length == 6) {
              len++;
          }
          len++;
      }
      return len;
    },
    deleteScrap(scrapId) {
      this.$emit('delete', scrapId);
    }
  }
};
</script>

<style>
</style>