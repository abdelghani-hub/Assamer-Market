package com.youcode.sudest_market.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
