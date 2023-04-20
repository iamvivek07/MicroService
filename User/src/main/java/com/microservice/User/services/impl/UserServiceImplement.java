package com.microservice.User.services.impl;

import com.microservice.User.Entites.Hotel;
import com.microservice.User.Entites.Ratings;
import com.microservice.User.Entites.User;
import com.microservice.User.Exceptions.ResourceNotFoundException;
import com.microservice.User.External.HotelService;
import com.microservice.User.Repositories.UserRepository;
import com.microservice.User.configuration.config;
import com.microservice.User.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    //since we need to use RestTemplate we need to create obj via autowiring
    //but we don't have bean of RestTemplate in our User Module
    //so we will create a class and there we will decalre a method with Bean annotation

    @Autowired
    private config config;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    private Logger logger = LoggerFactory.getLogger(UserServiceImplement.class);

    @Override
    public User create(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> res= userRepository.findAll();
        for (User usr:res){
            //here we will call rating Api to pull ratings for respective userId
            //to do that we will use RestTemplate Api
            //url->localhost:8082/ratings/user/2040d268-d37d-482a-8eb4-42325a15ecca
            Ratings[] ratings = config.restTemplate().getForObject("http://RATING-SERVICE/ratings/user/"+usr.getUserId(), Ratings[].class);
            List<Ratings> rating=Arrays.stream(ratings).toList();
            usr.setRatings(rating);
            logger.info("{}",rating);
            //now we need to fetch hotel details similarly as we fetched rating details
            for(Ratings rat:rating){
                Hotel hotel = config.restTemplate().getForObject("http://HOTEL-SERVICE/hotel/"+rat.getHotelId(),Hotel.class);
                //feign client implementation
                //Hotel hotel = hotelService.getHotel(rat.getHotelId());
                rat.setHotel(hotel);
            }
        }
        System.out.println("All User Message is send to Topic ");
        kafkaTemplate.send("AllUser",res.toString());
        return res;
    }

    @Override
    public User findById(String userId) {

        User res=userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException
                                ("User with given Id is not Present !!"+ userId));
        //here we will call rating Api to pull ratings for respective userId
        //to do that we will use RestTemplate Api
        //url->localhost:8082/ratings/user/2040d268-d37d-482a-8eb4-42325a15ecca
        Ratings[] ratings = config.restTemplate().getForObject("http://RATING-SERVICE/ratings/user/"+res.getUserId(), Ratings[].class);
        List<Ratings> rating=Arrays.stream(ratings).toList();
        res.setRatings(rating);
        logger.info("{}",rating);
        //now we need to fetch hotel details similarly as we fetched rating details
        for(Ratings rat:rating){
            Hotel hotel = config.restTemplate().getForObject("http://HOTEL-SERVICE/hotel/"+rat.getHotelId(),Hotel.class);
            //feign client implementation
            //Hotel hotel = hotelService.getHotel(rat.getHotelId());
            rat.setHotel(hotel);
        }
        return res;
    }

    @Override
    public void sendMessage(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        System.out.println("User body is "+user);
        System.out.println("Message is sent to UserTopic");
        kafkaTemplate.send("UserTopic",user.toString());
        userRepository.save(user);
    }
}
