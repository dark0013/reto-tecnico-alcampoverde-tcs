package com.acampoverde.ms_account_movement.domain.port.out;

public interface IRequestMessagePort {

    void sendMessage(String message);
}
