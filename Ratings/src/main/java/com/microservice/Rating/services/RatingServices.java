package com.microservice.Rating.services;

import com.microservice.Rating.entities.Ratings;

import java.util.List;

public interface RatingServices {

    //create rating
     Ratings createRating(Ratings ratings);

    //view All Ratings
    List<Ratings> viewAllRatings();

    //view particular Ratings

    Ratings getRatingById(String ratingId);


}
