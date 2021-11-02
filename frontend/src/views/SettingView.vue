<template>
  <v-container class="px-15" fluid>
    <h2><v-icon>mdi-file-tree</v-icon> 카테고리 설정</h2>
    <v-divider class="my-3"></v-divider>
    <v-row class="my-3">
      <v-treeview 
        :items="scrapTree" 
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
  </v-container>
</template>

<script>
export default {
  name: 'setting-view',
  data() {
    return {
      scrapTree: [
        {
          id: 1,
          name: 'Work',
          children: [
            { id: 2, name: 'Sub Work 1' },
            { id: 3, name: 'Sub Work 2' },          
          ]
        },
        {
          id: 4,
          name: 'Application',
          children: [
            { id: 5, name: 'Stopwatch App' }
          ]
        },
        {
          id: -1,
          name: '미분류',
          children: [
            { id: 6, name: '다이어트는 습관이다' }
          ]
        }
      ],
    }
  }
}
</script>