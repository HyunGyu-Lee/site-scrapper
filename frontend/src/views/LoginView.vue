<template>
  <v-container class="fill-height" fluid>
    <v-row>
      <v-col cols="12" sm="6">
        <v-form ref="form" lazy-validation>
          <v-text-field v-model="id" label="Account" prepend-icon="mdi-account" required></v-text-field>
          <v-text-field v-model="password" prepend-icon="mdi-lock"
            :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :type="passwordShow ? 'text' : 'password'"
            label="Password"
            @click:append="passwordShow = !passwordShow"
          ></v-text-field>
          <v-btn color="success" class="mr-4" @click="tryLogin">LOGIN</v-btn>
        </v-form>
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
      this.$store.dispatch('LOGIN', {id: this.id, password: this.password})
        .then(() => this.goScrapView())
    },
    goScrapView() {
      if (this.$store.getters.isAuthorized) {
        this.$router.push('/scraps')
      } 
    }
  }
};
</script>

<style>
</style>