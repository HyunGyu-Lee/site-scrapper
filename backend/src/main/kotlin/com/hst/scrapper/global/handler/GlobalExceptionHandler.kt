package com.hst.scrapper.global.handler

import com.hst.scrapper.global.exception.BaseException
import com.hst.scrapper.global.model.api.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author dlgusrb0808@gmail.com
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<Map<String, Any>> {
        exception.attribute["message"] = exception.message
        return ResponseEntity<Map<String, Any>>(exception.attribute, exception.status)
    }

}