<template>
    <v-dialog v-model="dialogShow">
        <v-card>
            <v-card-title>
                <span class="headline">회원가입</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field
                                v-model="registrationData.id"
                                label="Account"
                                prepend-icon="mdi-account"
                                required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field
                                v-model="registrationData.password"
                                prepend-icon="mdi-lock"
                                :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
                                :type="passwordShow ? 'text' : 'password'"
                                label="Password"
                                @click:append="passwordShow = !passwordShow"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field
                                v-model="registrationData.email"
                                label="Email"
                                prepend-icon="mdi-email"
                                append-icon="mdi-send"
                                @click:append="sendVerificationEmail()"
                                :disabled="emailVerification.isVerified == true"
                                required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
                <small>원활한 서비스 이용을 위해 이메일 인증을 필요로 합니다</small>
            </v-card-text>
            <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="blue darken-1" text @click="dialogShow = false">취소</v-btn>
                <v-btn color="blue darken-1" text @click="regist()">가입</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
    props: {
        value: {
            type: Boolean,
            required: true
        }
    },
    data: () => ({
        registrationData: {
            id: "",
            password: "",
            email: ""
        },
        emailVerification: {
            isVerified: false
        },
        passwordShow: false
    }),
    computed: {
        dialogShow: {
            set: function(newVal) {
                this.$emit("input", newVal);
            },
            get: function() {
                return this.value;
            }
        }
    },
    methods: {
        regist() {
            if (this.emailVerification.isVerified) {
                // TODO / 이현규 / 가입처리
            } else {
                // TODO / 이현규 / 이메일 인증필요 alert 노출
            }
        },
        sendVerificationEmail() {
            // TODO / 이현규 / 인증메일 전송 API 호출
            this.emailVerification.isVerified = true;
        }
    }
};
</script>

<style>
</style>