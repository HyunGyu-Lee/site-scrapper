package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * @author hyungyu.lee@nhn.com
 */
open class BaseException(
    val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR, message: String = ""
) : Exception(message) {
    private val attribute = HashMap<String, Any>()

    fun addAttribute(key: String, value: Any): BaseException {
        attribute[key] = value
        return this
    }
}