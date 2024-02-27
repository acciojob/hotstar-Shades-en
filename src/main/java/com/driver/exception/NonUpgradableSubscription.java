package com.driver.exception;

public class NonUpgradableSubscription extends RuntimeException{
    public NonUpgradableSubscription(String message){
        super(message);
    }
}
