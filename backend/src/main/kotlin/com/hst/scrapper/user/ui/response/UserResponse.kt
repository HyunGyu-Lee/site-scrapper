package com.hst.scrapper.user.ui.response

import com.hst.scrapper.user.domain.entity.User

/**
 * @author dlgusrb0808@gmail.com
 */
data class UserResponse(
    val id: String,
    val email: String,
    val name: String
) {
    companion object {
        fun fromEntity(entity: User): UserResponse {
            return UserResponse(entity.loginId, entity.email, entity.name)
        }
    }
}