package com.hst.scrapper.modules.user.ui.request

/**
 * @author dlgusrb0808@gmail.com
 */
data class SignupRequest(
    val loginId: String,
    val loginPassword: String,
    val email: String,
    val name: String
)