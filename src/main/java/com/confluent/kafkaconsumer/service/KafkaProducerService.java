package com.confluent.kafkaconsumer.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaProducerService {

    private final Properties kafkaProducerProperties;

    // Constructor injection using @Qualifier to specify which bean to inject
    @Autowired
    public KafkaProducerService(@Qualifier("kafkaProducerProperties") Properties kafkaProducerProperties) {
        this.kafkaProducerProperties = kafkaProducerProperties;
    }

    public void produceMessages(String topic) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProducerProperties);

        int messageCount = 0;
        while (true) {
            String message = "Message number " + messageCount;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, Integer.toString(messageCount), message);
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("Produced message: %s\n", message);
                } else {
                    System.err.println("Error producing message: " + exception.getMessage());
                }
            });
            messageCount++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
