package com.rakbank.exception;

public class CardTypeNotFoundException extends Exception {

    private String msg;

    public CardTypeNotFoundException(String msg) {
        super(msg);
    }

}
