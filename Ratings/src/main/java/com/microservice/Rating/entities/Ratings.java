package com.microservice.Rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Ratings {
    @Id
    private String ratingId;
    private int rating;
    private String userId;
    private String hotelId;
    private String feedback;
}
