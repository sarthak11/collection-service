package com.rakbank.exception;

public class TutionFeeNotFoundException extends Exception {

    private String msg;

    public TutionFeeNotFoundException(String msg) {
        super(msg);
    }
}
