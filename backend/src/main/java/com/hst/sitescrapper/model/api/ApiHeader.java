package com.hst.sitescrapper.model.api;

import org.springframework.http.HttpStatus;

/***
 * @author dlgusrb0808@gmail.com
 */
public class ApiHeader {
    private int code;
    private String message;

    private ApiHeader () {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ApiHeader of(HttpStatus status) {
        return of(status.value(), status.getReasonPhrase());
    }

    public static ApiHeader of(int code, String message) {
        ApiHeader header = new ApiHeader();
        header.code = code;
        header.message = message;
        return header;
    }

}
