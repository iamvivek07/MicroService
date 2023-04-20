package com.microservice.Rating.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }
    public ResourceNotFoundException() {
        super("Rating is not available for the given rating Id in the server!!");
    }
}
