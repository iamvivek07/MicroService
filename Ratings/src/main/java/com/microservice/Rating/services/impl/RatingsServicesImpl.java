package com.microservice.Rating.services.impl;

import com.microservice.Rating.Exceptions.ResourceNotFoundException;
import com.microservice.Rating.entities.Ratings;
import com.microservice.Rating.repositories.RatingRepository;
import com.microservice.Rating.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingsServicesImpl implements RatingServices {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Ratings createRating(Ratings ratings) {
        String ratingId = UUID.randomUUID().toString();
        ratings.setRatingId(ratingId);
        return this.ratingRepository.save(ratings);
    }

    @Override
    public List<Ratings> viewAllRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Ratings getRatingById(String ratingId) {
        return this.ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Ratings not found for the given Rating Id "+ratingId));
    }

    public List<Ratings> getRatingsByUserId(String userId){
        return ratingRepository.findByUserId(userId);
    }

    public List<Ratings> getRatingsByHotelId(String hotelId){
        return ratingRepository.findByHotelId(hotelId);
    }
}
