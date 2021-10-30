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
    <br>
    <v-row align="start" justify="start">
      <v-col cols="2"> 
        <h2><v-icon>mdi-widgets</v-icon> 내 스크랩</h2>
      </v-col>
      <v-spacer></v-spacer>
      <v-col cols="2" align="right"> 
        <v-btn-toggle v-model="viewType" mandatory dense>
          <v-btn>
            <v-icon>mdi-view-dashboard</v-icon>
          </v-btn>
          <v-btn>
            <v-icon>mdi-view-list</v-icon>
          </v-btn>
          <v-btn>
            <v-icon>mdi-file-tree</v-icon>
          </v-btn>
        </v-btn-toggle>
      </v-col>
    </v-row>
    <!-- Grid View Type -->
    <v-row v-if="viewTypes[viewType] == 'card'" align="start" justify="start">
      <v-col cols="3" v-for="scrap in scraps" :key="scrap.id">
        <scrap :scrap="scrap" :viewType="viewTypes[viewType]" v-on:delete="deleteScrap"></scrap>
      </v-col>
    </v-row>
    <v-row v-if="viewTypes[viewType] == 'list'">      
      <v-list three-line>
        <template v-for="scrap in scraps">
          <scrap :key="scrap.id" :scrap="scrap" :viewType="viewTypes[viewType]" v-on:delete="deleteScrap"></scrap>
          <v-divider :key="scrap.id + '_'"></v-divider>
        </template>
      </v-list>
    </v-row>
    <v-row v-if="viewTypes[viewType] == 'folder'">
      <scrap :scrap="scraps[0]" viewType="card"></scrap>
    </v-row>
    <v-row v-if="viewTypes[viewType] == 'tree'">
      <h1>TREE!!!</h1>
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
    scraps: [],
    viewTypes: {
      0: 'card',
      1: 'list',
      2: 'tree'
    },
    viewType: undefined
  }),
  created() {    
    this.findScraps();
  },
  methods: {
    findScraps() {
      this.$app.startLoading();
      Scraps.list(this.$store.getters.loginUser.id).then(response => {
        this.scraps = response.data.body;
        this.$app.finishLoading();
      })
    },
    addScrap() {      
      if (!this.scrapUrl) {
        this.$app.toast('URL을 정확히 입력하세요!');
        return;
      }
      this.$app.startLoading();
      Scraps.create({ url: this.scrapUrl, userId: this.$store.getters.loginUser.id }).then(() => {
        this.$app.finishLoading();
        this.findScraps();
      }, (error) => {
        this.$app.toast('스크랩 저장에 실패하였습니다.');
        this.$app.finishLoading();
        console.dir(error)
      });

      this.scrapUrl = '';
    },
    async deleteScrap(scrapId) {
      this.$app.startLoading();
      await Scraps.delete(scrapId)
      this.findScraps();
    }
  }
};
</script>
