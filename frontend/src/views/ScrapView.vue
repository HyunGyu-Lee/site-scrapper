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
        <scrap :scrap="scrap"></scrap>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Scrap from '@/components/Scrap';

export default {
  name: 'scrap-view',
  components: {
    Scrap
  },
  data: () => ({
    scrapUrl: '',
    scraps: []
  }),
  created() {
    this.findScraps();
  },
  methods: {
    findScraps: function() {
      this.$http.get('/api/scrap').then(response => {
        this.scraps = response.data.body;
      });
    },
    addScrap: function() {
      if (!this.scrapUrl) {
        alert('URL 을 입력하세요');
        return;
      }

      this.$http.post('/api/scrap', { url: this.scrapUrl }).then(response => {
        console.log(response);
        this.findScraps();
      });

      this.scrapUrl = '';
    }
  }
};
</script>
