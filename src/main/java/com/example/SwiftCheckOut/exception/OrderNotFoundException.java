package com.example.SwiftCheckOut.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String errorMsg){
        super(errorMsg);
    }
}

