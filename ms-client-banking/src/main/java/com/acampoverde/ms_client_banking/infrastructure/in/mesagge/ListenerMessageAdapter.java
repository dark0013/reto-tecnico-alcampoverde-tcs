package com.acampoverde.ms_client_banking.infrastructure.in.mesagge;

import com.acampoverde.ms_client_banking.domain.port.in.IlistenerMessagePort;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ListenerMessageAdapter implements IlistenerMessagePort {

//    @KafkaListener(topics = "account-movements", groupId = "mi-grupo-spring")
//    @SendTo("account-movements-reply")
    @Override
    public String listenMessage(String message) {
//        System.out.println("Received message: " + message);
//        System.out.println(String.format("#### -> Consumed message -> %s", message));

        System.out.println("Received request message: " + message);

        String response = "Respuesta al mensaje: " + message;
        System.out.println("Sending response message: " + response);
        return response;
    }

//    @KafkaListener(topics = "account-movements", groupId = "mi-grupo-spring")
//    public String listenMessage2(ConsumerRecord<String, String> record) {
//        String message = record.value();
//        System.out.println("Received request message: " + message);
//
//        String response = "Respuesta al mensaje: " + message;
//        System.out.println("Sending response message: " + response);
//
//        // Spring Kafka usará automáticamente las cabeceras para enviar la respuesta
//        return response;
//    }

    @KafkaListener(topics = "${app.kafka.request-topic}")
    @SendTo
    public String escucharSolicitud(String solicitud) {
        System.out.println("Solicitud recibida: " + solicitud);
        // Procesar la solicitud y generar una respuesta
        return "Respuesta para " + solicitud;
    }
}
