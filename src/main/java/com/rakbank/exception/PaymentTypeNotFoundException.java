package com.rakbank.exception;

public class PaymentTypeNotFoundException extends Exception {

    private String msg;

    public PaymentTypeNotFoundException(String msg) {
        super(msg);
    }

}
