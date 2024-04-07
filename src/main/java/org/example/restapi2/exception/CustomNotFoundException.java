package org.example.restapi2.exception;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message){
        super(message);
    }
}
