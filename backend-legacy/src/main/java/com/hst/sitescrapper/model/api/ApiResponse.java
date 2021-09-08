package com.hst.sitescrapper.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hst.sitescrapper.model.api.ApiHeader;
import org.springframework.http.HttpStatus;

/***
 * @author dlgusrb0808@gmail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"header", "body"})
public class ApiResponse<T> {
    private ApiHeader header;
    private T body;

    /***
     * basic constructor
     */
    public ApiResponse() {
        this(ApiHeader.of(HttpStatus.OK), null);
    }

    /***
     * constructor with header
     * @param code
     * @param message
     */
    public ApiResponse(int code, String message) {
        this(ApiHeader.of(code, message), null);
    }

    /***
     * constructor with only body
     * @param body
     */
    public ApiResponse(T body) {
        this(ApiHeader.of(HttpStatus.OK), body);
    }

    /***
     * core constructor with header, body
     * @param header
     * @param body
     */
    public ApiResponse(ApiHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public ApiHeader getHeader() {
        return header;
    }

    public T getBody() {
        return body;
    }

    public static ApiResponse ok() {
        return ok(null);
    }

    public static <T> ApiResponse<T> ok(T body) {
        return new ApiResponse<>(body);
    }
}
