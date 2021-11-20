<template>
  <v-app>
    {{loginUserInfo}}
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
    <v-main>
      <router-view></router-view>
    </v-main>
    <!-- Footer -->
    <v-footer app>
      <span>&copy; Team HST. All rights reserved.</span>
    </v-footer>

    <!-- Global Notification Message -->
    <v-snackbar v-model="alerts.visible" top multi-line :timeout="alerts.timeout">
      {{alerts.message}}
      <template v-slot:action="{ attrs }">
        <v-btn v-bind="attrs" dark text @click="alerts.visible = false">Close</v-btn>
      </template>      
    </v-snackbar>

    <!-- Global Loading Overay -->
    <v-overlay :value="loading">
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
  </v-app>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "App",
  components: {},
  data: () => ({
    drawer: null,
    alerts: {
      message: '',
      visible: false,
      timeout: 2000
    },
    loading: false
  }),
  computed: {
    ...mapState({
      loginUserInfo: 'user'
    })
  },
  created() {
    this.activeToastMessage();
    this.activeLoadingBar();
  },
  methods: {
    activeToastMessage() {
      this.$app.EventBus.$on('toast', (message) => {
        this.alerts.visible = false
        this.alerts.message = message
        this.alerts.visible = true
      })
    },
    activeLoadingBar() {
      this.$app.EventBus.$on('setLoadingState', (loadingState) => {
        this.loading = loadingState
      });
    },
    logout() {
      this.$store.dispatch("LOGOUT");
      this.$router.push('/login')
    }
  }
};
</script>
