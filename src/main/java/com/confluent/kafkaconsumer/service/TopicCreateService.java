package com.confluent.kafkaconsumer.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

@Service
public class TopicCreateService {

    public final Properties kafkaAdminProperties;

    @Autowired
    public TopicCreateService(@Qualifier("kafkaAdminProperties")Properties kafkaAdminProperties) {
        this.kafkaAdminProperties = kafkaAdminProperties;
    }

    public void createTopic(String topicName ) {
        this.createTopic(topicName, 10, 3);
    }

    public void createTopic(String topicName, int numPartitions) {
        this.createTopic(topicName, numPartitions, 3);
    }

    public void createTopic(String topicName, int numPartitions, int replicationFactor) {
        try(AdminClient adminClient = AdminClient.create(kafkaAdminProperties)) {
            NewTopic topic = new NewTopic(topicName, numPartitions, (short) replicationFactor);
            CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(topic));
            createTopicsResult.all().get();
            System.out.println("Created topic " + topic );
            System.out.println("Meta Data for created topic " + createTopicsResult );
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
