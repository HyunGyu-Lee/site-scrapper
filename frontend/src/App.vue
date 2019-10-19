<template>
  <v-app>
    <!-- Left Drawer -->
    <v-navigation-drawer v-model="drawer" app clipped v-if="this.$store.getters.isAuthorized">
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/men/1.jpg">
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>@{{loginUserInfo.name}}</v-list-item-title>
            <v-list-item-subtitle>
              <v-btn text @click="logout">로그아웃</v-btn>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
      <v-divider></v-divider>
      <v-list dense nav>
        <v-list-item to="/scraps">
          <v-list-item-action>
            <v-icon>mdi-widgets</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>스크랩</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/settings">
          <v-list-item-action>
            <v-icon>mdi-settings</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>설정</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <!-- Navigation Bar -->
    <v-app-bar app clipped-left color="blue darken-3" dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </v-app-bar>
    <!-- Content -->
    <v-content>
      <router-view></router-view>
    </v-content>
    <!-- Footer -->
    <v-footer app>
      <span>&copy; Team HST. All rights reserved.</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {},
  data: () => ({
    drawer: null,
    loginUserInfo: {}
  }),
  created() {
    this.loginUserInfo = this.$store.getters.loginUser;
  },
  methods: {
    logout() {
      this.$store.dispatch("LOGOUT");
      this.$router.push('/login')
    }
  }
};
</script>
