<template>
  <v-container class="px-15" fluid>
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
      <v-treeview 
        :items="categories" 
        open-on-click
        transition
      >
        <template v-slot:prepend="{ item }">
          <v-icon v-if="item.children">
            mdi-folder
          </v-icon>
          <v-img class="rounded-circle" v-if="!item.children"
            src="https://picsum.photos/id/11/500/300"
            width="36" height="36"
          >
          </v-img>
        </template>
      </v-treeview>
    </v-row>
    <v-dialog v-model="categoryDialog.show" width="500">
      <v-card>
        <v-card-title>카테고리 선택</v-card-title>
        <v-card-text>
          <v-treeview 
            :items="categories" 
            activatable
            transition
            dense
            open-all
            hoverable
            @update:active="onActive"
          >
            <template v-slot:prepend="{ item, open }">
              <v-icon v-if="item.children">
                {{open ? 'mdi-folder-open' : 'mdi-folder'}}
              </v-icon>
            </template>
            <!-- <template v-slot:append="{ item }">             
              <v-menu close-on-click>
                <template v-slot:activator="{on, attrs}">
                  <v-icon v-bind="attrs" v-on="on">mdi-dots-vertical</v-icon>
                </template>
                <v-list>
                  <v-list-item @click="openDialog(item, 'add')" link>       
                    <v-list-item-title>추가</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="openDialog(item, 'edit')" link>       
                    <v-list-item-title>이름 변경</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="openDialog(item, 'delete')" link>       
                    <v-list-item-title>삭제</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </template> -->
          </v-treeview>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="onDialogClose" text>취소</v-btn>
          <v-btn @click="onDialogOk" text>확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import Scrap from '@/components/Scrap';
import Scraps from '@/api/scraps';
import Category from '@/api/category';

export default {
  name: 'scrap-view',
  components: {
    Scrap
  },
  data: () => ({
    scrapUrl: '',
    scraps: [],
    categories: [],
    viewTypes: {
      0: 'card',
      1: 'list',
      2: 'tree'
    },
    viewType: undefined,
    categoryDialog: {
      show: false,
      selectedCategoryId: 0
    }
  }),
  created() {    
    this.loadUserScraps();
    this.loadUserCategories();
  },
  computed: {
    userId() {
      return this.$store.getters.loginUser.id
    }
  },
  methods: {
    async loadUserCategories() {
      this.$app.startLoading();
      let response = await Category.list(this.userId);
      this.categories = response.data.body;
      this.$app.finishLoading();
    },
    loadUserScraps() {
      this.$app.startLoading();
      Scraps.list(this.userId).then(response => {
        this.scraps = response.data.body;
        this.$app.finishLoading();
      })
    },
    addScrap() {      
      if (!this.scrapUrl) {
        this.$app.toast('URL을 정확히 입력하세요!');
        return;
      }
      this.categoryDialog.show = true;
    },
    processAddScrap() {
      this.$app.startLoading();
      Scraps.create({ url: this.scrapUrl, userId: this.userId, categoryId: this.categoryDialog.selectedCategoryId }).then(() => {
        this.$app.finishLoading();
        this.loadUserScraps();
        this.categoryDialog.show = false;
      }, () => {
        this.$app.toast('스크랩 저장에 실패하였습니다.');
        this.$app.finishLoading();
        this.categoryDialog.show = false;
      });
      this.scrapUrl = '';
    },
    async deleteScrap(scrapId) {
      this.$app.startLoading();
      await Scraps.delete(scrapId)
      this.loadUserScraps();
    },
    onDialogClose() {
      this.categoryDialog.show = false;
      this.scrapUrl = '';
    },
    onDialogOk() {
      if (this.categoryDialog.selectedCategoryId == 0) {
        this.$app.toast('카테고리를 선택하세요');
        return;
      }
      this.processAddScrap();
    },
    onActive(id) {
      if (id.length == 1) {
        this.categoryDialog.selectedCategoryId = id[0]
      } else {
        this.categoryDialog.selectedCategoryId = 0
      }
    }
  }
};
</script>
