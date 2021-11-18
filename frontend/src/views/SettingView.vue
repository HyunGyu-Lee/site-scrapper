<template>
  <v-container class="px-15" fluid>
    <h2><v-icon>mdi-file-tree</v-icon> 카테고리 설정</h2>
    <v-divider class="my-3"></v-divider>
    <v-row class="mt-3">
      <v-col cols="6">
        <v-treeview 
          :items="categories" 
          transition
          dense
          open-all
          hoverable
        >
          <template v-slot:prepend="{ item, open }">
            <v-icon v-if="item.children">
              {{open ? 'mdi-folder-open' : 'mdi-folder'}}
            </v-icon>
          </template>
          <template v-slot:append="{ item }">             
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
          </template>
        </v-treeview>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6">
        <v-btn width="100%" text @click="openDialog(undefined, 'add')">새 카테고리 추가</v-btn>
      </v-col>
    </v-row>
    <v-dialog v-model="dialog.show" width="500">
      <v-card>
        <v-card-title v-text="dialog.title" />
        <v-card-text>
          {{dialog.message}}
          <v-text-field v-model="dialog.input" v-if="dialog.mode != 'delete'"></v-text-field>
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
import Category from '@/api/category';

const ADD = 'add';
const EDIT = 'edit';
const DELETE = 'delete';

export default {
  name: 'setting-view',
  data() {
    return {
      categories: [],    
      dialog: {
        show: false,
        mode: ADD,
        title: '',
        message: '',
        input: '',
        data: undefined,
        titles: {
          [ADD]: '카테고리 추가',
          [EDIT]: '카테고리 이름 변경',
          [DELETE]: '카테고리 삭제'
        },
        messages: {
          [ADD]: '카테고리를 추가합니다.',
          [EDIT]: '카테고리 이름을 변경합니다.',
          [DELETE]: '카테고리를 삭제합니다.'
        }
      }
    }
  },
  created() {
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
    openDialog(item, mode) {
      this.dialog.mode = mode;
      this.dialog.title = this.dialog.titles[mode];
      this.dialog.message = this.dialog.messages[mode];
      this.dialog.data = item;
      this.dialog.show = true;
      switch(this.dialog.mode) {
        case ADD: this.dialog.input = ''; break;
        case EDIT: this.dialog.input = item.name; break;
      }
    },
    onDialogClose() {
      this.dialog.data = undefined;
      this.dialog.show = false;
    },
    async onDialogOk() {
      if (this.dialog.mode != DELETE) {
          if (!this.dialog.input) {
            alert('이름을 입력하세요');
            return;
          }
      }

      switch(this.dialog.mode) {
        case ADD: 
          await Category.create({
            parentId: this.dialog.data ? this.dialog.data.id: null, 
            name: this.dialog.input,
            userId: this.userId
          });
          break;
        case EDIT: 
          await Category.edit({
            id: this.dialog.data.id,
            name: this.dialog.input
          });
          break;
        case DELETE: 
          await Category.delete(this.dialog.data.id);
          break; 
      }
      this.loadUserCategories();
      this.onDialogClose();
    }
  }
}
</script>