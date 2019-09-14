<template>
  <v-container>
    <v-row align="start" justify="center">
      <v-col cols="6">
        <v-text-field v-model="scrapUrl" label="Scrap for" @keyup.enter.stop="addScrap"></v-text-field>
      </v-col>      
    </v-row>
    <v-row align="start" justify="center">
      <v-col cols="6">
        <h3><v-icon>mdi-widgets</v-icon> 스크랩 목록</h3>
      </v-col>
    </v-row>    
    <v-row align="start" justify="center" v-for="scrap in scraps" :key="scrap.id">
      <v-col cols="6">
        <v-card dark>
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
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "dashboard",
  components: {},
  data: () => ({
    scrapUrl: "",
    scraps: []
  }),
  created() {
    this.findScraps();
  },
  methods: {
    findScraps: function() {
      this.$http.get("/api/scrap").then(response => {
        this.scraps = response.data.body;
      });
    },
    addScrap: function() {
      if (!this.scrapUrl) {
        alert("URL 을 입력하세요");
        return;
      }

      this.$http.post("/api/scrap", { url: this.scrapUrl }).then(response => {
        console.log(response);
        this.findScraps();
      });

      this.scrapUrl = "";
    }
  }
};
</script>
