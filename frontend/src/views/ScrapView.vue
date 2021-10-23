<template>
  <v-container>
    <v-row align="start" justify="center" class="pt-0 mt-0">
      <v-col cols="12" sm="8" md="6" class="pt-0 pb-0 mt-0 mb-0">
        <v-text-field class="pt-0"
          v-model="scrapUrl" 
          append-outer-icon="mdi-send"
          label="새 스크랩" 
          @keyup.enter.stop="addScrap"
          @click:append-outer="addScrap">
        </v-text-field>
      </v-col>      
    </v-row>
    <v-row align="start" justify="start">
      <v-col cols="12" sm="8" md="6"> 
        <h3><v-icon>mdi-widgets</v-icon> 내 스크랩</h3>
      </v-col>
    </v-row>    
    <v-row align="start" justify="start">
      <v-col cols="3" v-for="scrap in scraps" :key="scrap.id">
        <scrap :scrap="scrap" v-on:delete="deleteScrap"></scrap>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Scrap from '@/components/Scrap';
import Scraps from '@/api/scraps';

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
    findScraps() {
      Scraps.list(this.$store.getters.loginUser.id).then(response => {
        console.log(response.data)
        this.scraps = response.data.body;
      })
    },
    addScrap() {
      if (!this.scrapUrl) {
        alert('URL 을 입력하세요');
        return;
      }
      Scraps.create({ url: this.scrapUrl, userId: this.$store.getters.loginUser.id }).then(() => {
        this.findScraps();
      }, (error) => {
        console.dir(error)
      });

      this.scrapUrl = '';
    },
    async deleteScrap(scrapId) {
      await Scraps.delete(scrapId)
      this.findScraps();
    }
  }
};
</script>
