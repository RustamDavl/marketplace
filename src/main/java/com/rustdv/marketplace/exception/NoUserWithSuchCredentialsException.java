package com.rustdv.marketplace.exception;

public class NoUserWithSuchCredentialsException extends RuntimeException {
    public NoUserWithSuchCredentialsException(String message) {
        super(message);
    }
}
