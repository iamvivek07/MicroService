package com.microservice.User.services;

import com.microservice.User.Entites.User;

import java.util.List;

public interface UserService {

    //create
    User create(User user);

    //list all
    List<User> getAllUser();

    //findById
    User findById(String userId);

    void sendMessage(User user);
}
