package com.hst.scrapper.user.domain.exception

import com.hst.scrapper.global.exception.ForbiddenException

/**
 * @author dlgusrb0808@gmail.com
 */
class AuthorizationFailException(message: String = "Fail to authorization") : ForbiddenException(message)