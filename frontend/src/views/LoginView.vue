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
                        <v-btn color="success" class="mr-4" @click="openRegistDialog()">가입</v-btn>
                        <v-btn color="primary" class="mr-4" @click="tryLogin()">로그인</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
        <v-snackbar v-model="loginErrorShow" top vertical>
            로그인에 실패하였습니다.
            <v-btn dark text @click="loginErrorShow = false">닫기</v-btn>
        </v-snackbar>
        <regist-dialog v-model="registDialogShow"></regist-dialog>
    </v-container>
</template>

<script>
import RegistDialog from '@/components/auth/RegistDialog'

export default {
    components: {RegistDialog},
    data: () => ({
        id: "",
        password: "",
        passwordShow: false,
        loginErrorShow: false,
        registDialogShow: false
    }),
    methods: {
        openRegistDialog() {
            this.registDialogShow = true;
        },
        tryLogin() {
            this.$store
                .dispatch("LOGIN", { id: this.id, password: this.password })
                .then(() => this.goScrapView())
                .catch(() => (this.loginErrorShow = true));
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