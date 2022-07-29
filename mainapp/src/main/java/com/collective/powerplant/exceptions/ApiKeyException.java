package com.collective.powerplant.exceptions;

import lombok.Data;

@Data
public class ApiKeyException extends RuntimeException{

    private String response;

    public ApiKeyException(String response){
        super(response);
        this.response=response;
    }


}
