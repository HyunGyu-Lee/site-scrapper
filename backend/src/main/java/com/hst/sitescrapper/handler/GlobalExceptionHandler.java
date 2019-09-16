package com.hst.sitescrapper.handler;

import com.hst.sitescrapper.exception.ServiceException;
import com.hst.sitescrapper.exception.UnAuthorizedException;
import com.hst.sitescrapper.model.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    public ApiResponse handleUnAuthorizedException(UnAuthorizedException e) {
        return new ApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResponse handleServiceException(ServiceException e) {
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
