package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * @author dlgusrb0808@gmail.com
 */
open class BadRequestException(message: String = "") : BaseException(HttpStatus.BAD_REQUEST, message)