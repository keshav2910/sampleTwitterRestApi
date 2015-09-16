package com.exception;

import java.util.Map;

/**
 * Created by kumarke on 9/14/15.
 */
public class AppException extends RuntimeException {
    private int statusCode;
    private String errMessage;

    public AppException(String message, int statusCode, String errMessage) {
        super(message);
        this.errMessage = errMessage;
        this.statusCode = statusCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
