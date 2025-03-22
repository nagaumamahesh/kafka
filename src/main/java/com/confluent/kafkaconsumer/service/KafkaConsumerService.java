package com.confluent.kafkaconsumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaConsumerService {

    public static Properties consumerConfig() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "pkc-619z3.us-east1.gcp.confluent.cloud:9092");
        props.put("group.id", "order001");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "false");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        String apiKey = "TDM7H54V3TJZJGTU";
        String apiSecret = "BwajMC/fihBlGh49+egir0U7GeJ4j7vFwiBzb34dp+8Qm56PK0A5BO0SIq7paNje";
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + apiKey + "\" password=\"" + apiSecret + "\";");

        return props;
    }

    public static void init() {
        Thread consumerThread = new Thread(() -> consumeMessages());
        consumerThread.start();
    }


    private static void consumeMessages() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig());
        consumer.subscribe(Collections.singletonList("data"));
        Duration timeout = Duration.ofMillis(1000);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            if (records.isEmpty()) {
                System.out.println("No messages available in the topic at this moment.");
            } else {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consumed message: %s\n", record);
                }
                consumer.commitSync();
            }
        }
    }

    // Producer logic
    private static void produceMessages() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerConfig());

        int messageCount = 0;
        while (true) {
            String message = "Message number " + messageCount;
            ProducerRecord<String, String> record = new ProducerRecord<>("data", Integer.toString(messageCount), message);
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





    public static Properties producerConfig() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "pkc-619z3.us-east1.gcp.confluent.cloud:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        String apiKey = "TDM7H54V3TJZJGTU";
        String apiSecret = "BwajMC/fihBlGh49+egir0U7GeJ4j7vFwiBzb34dp+8Qm56PK0A5BO0SIq7paNje";
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + apiKey + "\" password=\"" + apiSecret + "\";");

        return props;
    }
}
