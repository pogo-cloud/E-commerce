package com.example.ecomproject.Exception;

public class EmailAlreadyPresentException extends Exception{
    public EmailAlreadyPresentException(String message){
        super(message);
    }
}
