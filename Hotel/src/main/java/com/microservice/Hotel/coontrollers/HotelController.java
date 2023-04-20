package com.microservice.Hotel.coontrollers;

import com.microservice.Hotel.entities.Hotel;
import com.microservice.Hotel.services.impl.HotelServicesImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelServicesImplementation hotelServicesImplementation;
    //create Hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServicesImplementation.createHotel(hotel));
    }
    //List All Hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> viewAllHotels(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(hotelServicesImplementation.listAllHotels());
    }

    //List Hotel by Id
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> viewHotelById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(hotelServicesImplementation.listHotelById(hotelId));
    }
}
