package com.hst.scrapper.global.model.api

import com.hst.scrapper.global.exception.BaseException
import org.springframework.http.HttpStatus

/**
 * @author dlgusrb0808@gmail.com
 */
data class ApiResponse<T>(
    val header: ApiHeader,
    val body: T
) {
    companion object {
        fun from(status: HttpStatus): ApiResponse<Any?> {
            return of(status, null)
        }

        fun <T> of(status: HttpStatus, body: T): ApiResponse<T> {
            return ApiResponse(ApiHeader.from(status), body)
        }

        fun <T> of(statusCode: Int, message: String, body: T): ApiResponse<T> {
            return ApiResponse(ApiHeader(statusCode, message), body)
        }

        fun success(): ApiResponse<Any?> {
            return from(HttpStatus.OK)
        }

        fun <T> success(body: T): ApiResponse<T> {
            return of(HttpStatus.OK, body)
        }

        fun error(exception: BaseException): ApiResponse<Map<String, Any>> {
            return of(exception.status.value(), exception.message, exception.attribute)
        }
    }
}