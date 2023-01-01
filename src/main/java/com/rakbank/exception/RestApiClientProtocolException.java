package com.rakbank.exception;

public class RestApiClientProtocolException extends RuntimeException {

    private final int code;

    private final Object status;

    public RestApiClientProtocolException(String message, int code, Object status) {
        super(message);
        this.code = code;
        this.status = status;
    }

}
