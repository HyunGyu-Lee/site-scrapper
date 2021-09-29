package com.hst.scrapper.global.model.api

import org.springframework.http.HttpStatus

/**
 * @author dlgusrb0808@gmail.com
 */
data class ApiHeader(
    val code: Int,
    val message: String
) {
    companion object {
        fun from(status: HttpStatus): ApiHeader {
            return ApiHeader(status.value(), status.reasonPhrase)
        }
    }
}