package com.acampoverde.ms_account_movement.infraestructure.out.messaging.exception;

public class KafkaProducerRecivedException extends RuntimeException {

    public KafkaProducerRecivedException(String message) {
        super(message);
    }

    public KafkaProducerRecivedException(String message, Throwable cause) {
        super(message, cause);
    }
}
