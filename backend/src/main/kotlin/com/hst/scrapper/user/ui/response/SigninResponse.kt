package com.hst.scrapper.user.ui.response

import com.hst.scrapper.user.ui.response.UserResponse

/**
 * @author dlgusrb0808@gmail.com
 */
data class SigninResponse(
    val authorizedToken: String,
    val user: UserResponse
)