package com.acampoverde.ms_account_movement.application.exception;

public class AccountMovementNotFoundException extends RuntimeException {
    public AccountMovementNotFoundException(String message) {
        super(message);
    }
}
