package com.microservice.User.Entites;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ratings {

    @Id
    private String ratingId;
    private int rating;
    private String userId;
    private String hotelId;
    private String feedback;
    private Hotel hotel;
}
