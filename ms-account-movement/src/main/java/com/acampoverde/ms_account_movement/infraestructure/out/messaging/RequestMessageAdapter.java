package com.acampoverde.ms_account_movement.infraestructure.out.messaging;

import com.acampoverde.ms_account_movement.domain.port.out.IRequestMessagePort;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class RequestMessageAdapter implements IRequestMessagePort {

  /*  private final KafkaTemplate<String, String> kafkaTemplate;
    //private final String requestTopic;
    private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;
*/
    ///
    @Autowired
    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    @Value("${app.kafka.request-topic}")
    private String requestTopic;

    @Value("${app.kafka.reply-topic}")
    private String replyTopic;

   /* public RequestMessageAdapter(
            KafkaTemplate<String, String> kafkaTemplate, ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate,
            @Value("${kafka.topic.account-movements}") String requestTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.replyingKafkaTemplate = replyingKafkaTemplate;
        this.requestTopic = requestTopic;
    }*/

    @Override
    public void sendMessage(String message) throws ExecutionException, InterruptedException, TimeoutException {
//        System.out.println("Sending message to topic: " + requestTopic + " with content: " + message);
//        //kafkaTemplate.send(requestTopic, message);
//
//        ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, message);
//        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
//        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS); // espera max 10 segundos
//
//        System.out.println("Received reply: " + consumerRecord.value());
//        //return consumerRecord.value();


        String resp = enviarYRecibir("ahorita envio esta notaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println("Respuesta recibida: " + resp);
    }

    public String enviarYRecibir(String solicitud) throws InterruptedException, ExecutionException, TimeoutException {
        ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, solicitud);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));

        RequestReplyFuture<String, String, String> future = replyingKafkaTemplate.sendAndReceive(record);

        SendResult<String, String> sendResult = future.getSendFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Mensaje enviado al tópico: " + sendResult.getRecordMetadata().topic());

        ConsumerRecord<String, String> consumerRecord = future.get(10, TimeUnit.SECONDS);
        return consumerRecord.value();
    }
}
