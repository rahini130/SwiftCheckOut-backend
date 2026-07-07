package com.example.SwiftCheckOut.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg)
    {
        super(msg);
    }
}
