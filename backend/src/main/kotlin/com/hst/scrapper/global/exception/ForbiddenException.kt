package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * @author dlgusrb0808@gmail.com
 */
open class ForbiddenException(message: String = "") : BaseException(HttpStatus.FORBIDDEN, message)