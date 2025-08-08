package com.acampoverde.ms_account_movement.application.exception;

public class InvalidMovementTypeException extends RuntimeException {
    public InvalidMovementTypeException(String message) {
        super(message);
    }
}
