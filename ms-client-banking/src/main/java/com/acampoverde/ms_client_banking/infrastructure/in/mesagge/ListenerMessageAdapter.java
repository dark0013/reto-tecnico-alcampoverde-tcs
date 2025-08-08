package com.acampoverde.ms_client_banking.infrastructure.in.mesagge;

import com.acampoverde.ms_client_banking.domain.model.Customer;
import com.acampoverde.ms_client_banking.domain.port.in.ICustomerServicePort;
import com.acampoverde.ms_client_banking.domain.port.in.IlistenerMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListenerMessageAdapter implements IlistenerMessagePort {

    private final ICustomerServicePort customerService;

    @KafkaListener(topics = "${app.kafka.request-topic}")
    @SendTo
    @Override
    public String listenMessage(String message) {
        if (message == null || message.isEmpty()) {
            return "false";
        }
        Customer customer = customerService.findCustomerById(Integer.valueOf(message));
        if (customer == null) {
            return "false";
        }
        return "true";
    }

}
