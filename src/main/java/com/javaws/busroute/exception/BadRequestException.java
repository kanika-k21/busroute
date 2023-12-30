package com.javaws.busroute.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String error) {
        super(error);
    }
}
