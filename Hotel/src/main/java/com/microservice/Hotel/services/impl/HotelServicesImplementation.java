package com.microservice.Hotel.services.impl;

import com.microservice.Hotel.Exceptions.RecordNotFoundException;
import com.microservice.Hotel.entities.Hotel;
import com.microservice.Hotel.repositories.HotelRepository;
import com.microservice.Hotel.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServicesImplementation implements HotelServices {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> listAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel listHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new RecordNotFoundException("Hotel Doesn't Exist for the hotel id "+ hotelId));
    }
}
