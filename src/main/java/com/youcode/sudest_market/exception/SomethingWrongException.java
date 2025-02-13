package com.youcode.sudest_market.exception;

public class SomethingWrongException extends RuntimeException {
    public SomethingWrongException() {
        super("Something went wrong");
    }
}
