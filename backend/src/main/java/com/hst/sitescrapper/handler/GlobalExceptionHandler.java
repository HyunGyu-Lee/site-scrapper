package com.hst.sitescrapper.handler;

import com.hst.sitescrapper.model.exception.ServiceException;
import com.hst.sitescrapper.model.exception.UnAuthorizedException;
import com.hst.sitescrapper.model.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handleUnAuthorizedException(UnAuthorizedException e) {
        return new ApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleServiceException(ServiceException e) {
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
