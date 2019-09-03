package com.hst.sitescrapper.model.api;

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

    public static ApiResponse of(int code, String message) {
        return of(code, message, null);
    }

    public static ApiResponse of(int code, String message, Object body) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setHeader(ApiHeader.of(code, message));
        apiResponse.setBody(body);

        return apiResponse;
    }


}
