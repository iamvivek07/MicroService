package com.microservice.User.controllers;

import com.microservice.User.Entites.User;
import com.microservice.User.services.impl.UserServiceImplement;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImplement userServiceImplement;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImplement.create(user));
    }

    //List all users
    @GetMapping
    public ResponseEntity<List<User>> listAllUser(){
        return ResponseEntity.ok(userServiceImplement.getAllUser());
    }

    //get one User
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelCircuit",fallbackMethod = "issueInRatingHotel")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceImplement.findById(userId));
    }
    //creating fallback method
    public ResponseEntity<User> issueInRatingHotel(String userId, Exception ex){
        User user= User
                .builder()
                .userName("Dummy")
                .userInfo("Dummy-User")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/view")
    public void sendUser(@RequestBody User user){
        System.out.println("message recieved from client and is sent through kafka producer...");
        userServiceImplement.sendMessage(user);
    }
}
