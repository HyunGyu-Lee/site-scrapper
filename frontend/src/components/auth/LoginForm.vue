<template>
    <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="8">
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
        </v-col>
        <v-col cols="12" sm="8" md="8">
            <v-btn color="primary" block class="mr-4" @click="tryLogin()">로그인</v-btn>
        </v-col>
        <v-col cols="12" sm="8" md="8">
            <v-btn color="primary" small text block @click="clickRegistButton()">계정이 없는 경우 신규가입</v-btn>
        </v-col>
    </v-row>
</template>

<script>
export default {
    data: () => ({
        id: "",
        password: "",
        passwordShow: false
    }),
    methods: {
        clickRegistButton() {
            this.$emit("open-regist-dialog");
        },
        tryLogin() {
            this.$store
                .dispatch("LOGIN", { id: this.id, password: this.password })
                .then(() => this.$emit("login-success"))
                .catch(e => this.$emit("login-error", e));
        }
    }
};
</script>

<style>
</style>