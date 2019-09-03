package com.hst.sitescrapper.model.api;

public class ApiHeader {

    private Integer code;   // business code not http code
    private String message; // business message not http message

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ApiHeader of(Integer code, String message) {
        ApiHeader apiHeader = new ApiHeader();
        apiHeader.setCode(code);
        apiHeader.setMessage(message);
        return apiHeader;
    }
}