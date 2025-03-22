package com.confluent.kafkaconsumer;

import com.confluent.kafkaconsumer.service.KafkaConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerApplication implements CommandLineRunner {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @Override
    public void run(String... args) throws Exception {
        kafkaConsumerService.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}
