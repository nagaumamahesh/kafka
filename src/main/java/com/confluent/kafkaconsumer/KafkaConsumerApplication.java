package com.confluent.kafkaconsumer;

import com.confluent.kafkaconsumer.service.KafkaConsumerService;
import com.confluent.kafkaconsumer.service.KafkaProducerService;
import com.confluent.kafkaconsumer.service.TopicCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerApplication implements CommandLineRunner {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Autowired
    TopicCreateService topicCreateService;

    @Override
    public void run(String... args) {
        String topic = "Topic";
        topicCreateService.createTopic(topic);
       kafkaProducerService.produceMessages(topic);
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }


}
