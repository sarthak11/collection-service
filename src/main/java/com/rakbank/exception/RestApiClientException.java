package com.rakbank.exception;

public class RestApiClientException extends RuntimeException {

    public RestApiClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
