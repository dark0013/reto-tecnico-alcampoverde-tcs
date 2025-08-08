package com.acampoverde.ms_account_movement.infraestructure.out.messaging.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfiguration {

    @Value("${app.kafka.reply-topic}")
    private String replyTopic;

    @Value("${spring.kafka.consumer.group-id}")
    private String replyGroupId;

//    @Bean
//    public ConcurrentMessageListenerContainer<String, String> replyContainer(
//            ConsumerFactory<String, String> consumerFactory) {
//        ContainerProperties containerProperties = new ContainerProperties("account-movements-reply");
//        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties);
//    }
//
//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
//            ProducerFactory<String, String> pf,
//            ConcurrentMessageListenerContainer<String, String> replyContainer) {
//        return new ReplyingKafkaTemplate<>(pf, replyContainer);
//    }

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
            ProducerFactory<String, String> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, String> factory) {
        // El contenedor debe escuchar en el tópico de respuesta
        ConcurrentMessageListenerContainer<String, String> replyContainer = factory.createContainer(replyTopic);
        replyContainer.getContainerProperties().setGroupId(replyGroupId);
        return new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
