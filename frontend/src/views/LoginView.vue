<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card class="elevation-12">
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>로그인</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-form ref="form" lazy-validation>
              <v-text-field v-model="id" label="Account" prepend-icon="mdi-account" required></v-text-field>
              <v-text-field
                v-model="password"
                prepend-icon="mdi-lock"
                :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
                :type="passwordShow ? 'text' : 'password'"
                label="Password"
                @click:append="passwordShow = !passwordShow"
              ></v-text-field>              
            </v-form>
          </v-card-text>
          <v-card-actions>
            <div class="flex-grow-1"></div>
            <v-btn color="success" class="mr-4" @click="tryLogin">REGIST</v-btn>
            <v-btn color="primary" class="mr-4" @click="tryLogin">LOGIN</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    id: "",
    password: "",
    passwordShow: false
  }),
  methods: {
    tryLogin() {
      this.$store
        .dispatch("LOGIN", { id: this.id, password: this.password })
        .then(() => this.goScrapView());
    },
    goScrapView() {
      if (this.$store.getters.isAuthorized) {
        this.$router.push("/scraps");
      }
    }
  }
};
</script>

<style>
</style>