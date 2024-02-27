package com.driver.exception;

public class ProductionHouseNotFoundException extends RuntimeException{
    public ProductionHouseNotFoundException(String message){
        super(message);
    }
}
