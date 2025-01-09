package com.youcode.sudest_market.exception;

public class NullOrBlankArgException extends RuntimeException {

    private String arg;
    public NullOrBlankArgException(String arg) {
        super(arg + " cannot be null or blank");
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }
}
