package com.phonedirectory.model;

public class DataInconsistentException extends RuntimeException {

    private final String message;

    public DataInconsistentException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}