<template>
    <v-container class="fill-height" fluid>
        <v-layout text-center wrap>
            <v-flex xs12 class="d-flex align-center">
                <v-img src="@/assets/logo_3.png" class="my-3" contain height="200"></v-img>
            </v-flex>
            <login-form 
                v-on:open-regist-dialog="openRegistDialog"
                v-on:login-error="processLoginError"
                v-on:login-success="processLoginSuccess"
            ></login-form>
            <v-snackbar v-model="loginErrorShow" top vertical>
                {{loginFailMessage}}
                <v-btn dark text @click="loginErrorShow = false">닫기</v-btn>
            </v-snackbar>
            <regist-dialog v-model="registDialogShow"></regist-dialog>
        </v-layout>
    </v-container>
</template>

<script>
import LoginForm from '@/components/auth/LoginForm'
import RegistDialog from '@/components/auth/RegistDialog'

export default {
    components: { LoginForm, RegistDialog },
    data: () => ({
        loginErrorShow: false,
        loginFailMessage: '로그인에 실패하였습니다.',
        registDialogShow: false
    }),
    methods: {
        openRegistDialog() {
            this.registDialogShow = true;
        },
        processLoginError(e) {
            this.loginErrorShow = true;
            console.dir(e);
        },
        processLoginSuccess() {
            if (this.$store.getters.isAuthorized) {
                this.$router.push("/scraps");
            }
        }
    }
};
</script>

<style>
</style>