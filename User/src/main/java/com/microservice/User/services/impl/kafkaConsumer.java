package com.microservice.User.services.impl;

import com.microservice.User.Entites.User;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class kafkaConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "UserTopic", groupId = "User")
    public void consumeduser(String message){
            LOGGER.info("Consumed users are "+message);
           System.out.println("Consumed user..."+message);
    }
    @KafkaListener(topics = "AllUser", groupId = "User")
    public void consumedAllUser(String message){
        LOGGER.info("All users are "+message);
        System.out.println("users are..."+message);
    }
}
