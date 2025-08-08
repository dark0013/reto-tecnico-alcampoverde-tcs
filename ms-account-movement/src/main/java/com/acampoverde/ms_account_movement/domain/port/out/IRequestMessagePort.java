package com.acampoverde.ms_account_movement.domain.port.out;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IRequestMessagePort {

    String sendMessage(String message);
}
