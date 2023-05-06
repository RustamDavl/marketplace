package com.rustdv.marketplace.exception;

public class NoSuchElementInEnumException extends RuntimeException {

    private String message;

    public NoSuchElementInEnumException(String message) {
        super(message);
    }
}
