package com.microservice.Rating.controllers;

import com.microservice.Rating.entities.Ratings;
import com.microservice.Rating.services.impl.RatingsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/ratings")
public class RatingsController {
    @Autowired
    private RatingsServicesImpl ratingsServices;
    //create Ratings
    @PostMapping
    public ResponseEntity<Ratings> createRatings(@RequestBody Ratings rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingsServices.createRating(rating));
    }

    //view all ratings
    @GetMapping
    public ResponseEntity<List<Ratings>> viewAllRatings(){
        List<Ratings> result =ratingsServices.viewAllRatings();
        Collections.sort(result, new Comparator<Ratings>() {
            @Override
            public int compare(Ratings o1, Ratings o2) {
                return o1.getRating()-o2.getRating();
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //view rating by id

    @GetMapping("/{ratingId}")
    public ResponseEntity<Ratings> viewRatingById(@RequestParam String ratingId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingsServices.getRatingById(ratingId));
    }

    //view RatingsBy UserId

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ratings>> viewRatingByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingsServices.getRatingsByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Ratings>> viewRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingsServices.getRatingsByHotelId(hotelId));
    }


}
