package com.confluent.kafkaconsumer.properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProperties {


    @Bean(name = "kafkaConsumerProperties")
    public Properties consumerConfig() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "pkc-619z3.us-east1.gcp.confluent.cloud:9092");
        props.put("group.id", "order0013");
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

    @Bean(name = "kafkaProducerProperties")
    public Properties kafkaProducerProperties() {
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


    @Bean(name = "kafkaAdminProperties")
    public Properties kafkaAdminProperties() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "pkc-619z3.us-east1.gcp.confluent.cloud:9092");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        String apiKey = "TDM7H54V3TJZJGTU";
        String apiSecret = "BwajMC/fihBlGh49+egir0U7GeJ4j7vFwiBzb34dp+8Qm56PK0A5BO0SIq7paNje";
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + apiKey + "\" password=\"" + apiSecret + "\";");

        return props;
    }
}
