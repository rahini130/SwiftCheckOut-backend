package com.example.SwiftCheckOut.exception;

public class InventoryNotFoundException extends RuntimeException {
     public InventoryNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
