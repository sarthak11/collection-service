package com.rakbank.exception;

public class FeeReceiptException extends Exception {

    private String msg;

    public FeeReceiptException(String msg) {
        super(msg);
    }

}
