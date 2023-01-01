package com.rakbank.exception;

public class FeeCollectionException extends Exception {

    private String msg;

    public FeeCollectionException(String msg) {
        super(msg);
    }

}
