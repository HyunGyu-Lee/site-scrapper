package com.hst.scrapper.modules.user.domain.repo

import com.hst.scrapper.modules.user.domain.entity.User

/**
 * @author dlgusrb0808@gmail.com
 */
interface UserRepository {
    fun findById(id: Long): User?

    fun findByEmail(email: String): User?

    fun findByLoginId(loginId: String): User?

    fun save(user: User): User
}