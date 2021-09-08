package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * @author dlgusrb0808@gmail.com
 */
class NotFoundException(message: String = "") : BaseException(HttpStatus.NOT_FOUND, message)