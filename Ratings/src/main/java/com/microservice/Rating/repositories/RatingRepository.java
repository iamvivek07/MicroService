package com.microservice.Rating.repositories;

import com.microservice.Rating.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Ratings,String> {
    //view Ratings by UserId

    List<Ratings> findByUserId(String userId);

    //view Ratings by HotelId

    List<Ratings> findByHotelId(String HotelId);
}
