package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * @author hyungyu.lee@nhn.com
 */
open class BaseException(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR, override val message: String = ""
) : Exception(message) {
    val attribute = HashMap<String, Any>()

    fun addAttribute(key: String, value: Any): BaseException {
        attribute[key] = value
        return this
    }
}