package com.collective.powerplant.exceptions;

public class ShortUrlNotFoundException extends RuntimeException{
    private String response;

    public ShortUrlNotFoundException(String response){
        super(response);
        this.response=response;
    }
}
