package com.hst.scrapper.modules.user.ui.response

/**
 * @author dlgusrb0808@gmail.com
 */
data class SigninResponse(
    val authorizedToken: String,
    val user: UserResponse
)