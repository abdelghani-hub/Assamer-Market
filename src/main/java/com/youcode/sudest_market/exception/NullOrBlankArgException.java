package com.youcode.sudest_market.exception;

public class NullOrBlankArgException extends RuntimeException {
    public NullOrBlankArgException(String arg) {
        super(arg + " cannot be null or blank");
    }
}
