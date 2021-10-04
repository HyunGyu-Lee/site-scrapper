package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * @author dlgusrb0808@gmail.com
 */
open class DuplicationResourceException(message: String = "") : BaseException(HttpStatus.CONFLICT, message)