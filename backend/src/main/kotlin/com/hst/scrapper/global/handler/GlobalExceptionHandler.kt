package com.hst.scrapper.global.handler

import com.hst.scrapper.global.exception.BaseException
import com.hst.scrapper.global.model.api.ApiResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-09-30
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ApiResponse<Map<String, Any>> {
        return ApiResponse.of(exception.status.value(), exception.message, exception.attribute)
    }

}