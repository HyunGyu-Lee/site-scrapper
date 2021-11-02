package com.hst.scrapper.modules.user.domain.repo

import com.hst.scrapper.modules.user.domain.entity.User
import javax.servlet.http.HttpServletRequest

/**
 * @author dlgusrb0808@gmail.com
 */
interface AuthTokenRepository {

    fun publishToken(user: User): String

    fun getToken(request: HttpServletRequest): String?

    fun isValidToken(token: String?): Boolean

    fun getUserIdFromToken(token: String): Long

}