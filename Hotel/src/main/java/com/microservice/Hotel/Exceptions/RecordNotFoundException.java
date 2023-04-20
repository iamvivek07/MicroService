package com.microservice.Hotel.Exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String s) {
        super(s);
    }
    public RecordNotFoundException() {
        super("Hotel not Found for that Id!!");
    }
}
