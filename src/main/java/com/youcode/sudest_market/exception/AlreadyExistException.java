package com.youcode.sudest_market.exception;

public class AlreadyExistException extends RuntimeException {

    private String field;
    public AlreadyExistException(String field, String value) {
        super(String.format("%s with value '%s' already exists", field, value));
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
