package com.acampoverde.ms_account_movement.infraestructure.out.messaging;

import com.acampoverde.ms_account_movement.domain.port.out.IRequestMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestMessageAdapter implements IRequestMessagePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public RequestMessageAdapter(KafkaTemplate<String, String> kafkaTemplate,
                                 @Value("${kafka.topic.account-movements}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message to topic: " + topic + " with content: " + message);
        kafkaTemplate.send(topic, message);
    }
}
