package com.microservice.Hotel.services;

import com.microservice.Hotel.entities.Hotel;

import java.util.List;

public interface HotelServices {

    //create HotelRecord
    Hotel createHotel(Hotel hotel);

    //list all hotels

    List<Hotel> listAllHotels();

    //List Hotel by Id

    Hotel listHotelById(String hotelId);
}
