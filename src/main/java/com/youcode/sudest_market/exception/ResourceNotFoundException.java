package com.youcode.sudest_market.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
