package com.microservice.User.objectSerDe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.User.Entites.User;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSeriliazation implements Serializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, User data) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, User data) {

        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        }catch (Exception e){
            throw new SerializationException(e);
        }

    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
