package com.hst.sitescrapper.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private ApiHeader header;
    private Object body;

    public ApiHeader getHeader() {
        return header;
    }

    public void setHeader(ApiHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static ApiResponse of(HttpStatus status) {
        return of (status.value(), status.getReasonPhrase(), null);
    }

    public static ApiResponse of(HttpStatus status, String message) {
        return of (status.value(), message, null);
    }

    public static ApiResponse of(HttpStatus status, Object body) {
        return of (status.value(), status.getReasonPhrase(), body);
    }

    public static ApiResponse of(HttpStatus status, String message, Object body) {
        return of (status.value(), message, body);
    }

    public static ApiResponse of(int code, String message, Object body) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setHeader(ApiHeader.of(code, message));
        apiResponse.setBody(body);

        return apiResponse;
    }
}
