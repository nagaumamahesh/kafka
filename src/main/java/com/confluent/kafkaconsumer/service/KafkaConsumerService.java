package com.confluent.kafkaconsumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaConsumerService {

    private final Properties consumerProperties;

    // Constructor injection using @Qualifier to specify the correct bean
    @Autowired
    public KafkaConsumerService(@Qualifier("kafkaConsumerProperties") Properties consumerProperties) {
        this.consumerProperties = consumerProperties;
    }

    public void consumeMessages(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(Collections.singletonList(topic));
        Duration timeout = Duration.ofMillis(1000);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            if (records.isEmpty()) {
                System.out.println("No messages available in the topic at this moment.");
            } else {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consumed message: %s\n", record);
                }
            }
        }
    }
}
