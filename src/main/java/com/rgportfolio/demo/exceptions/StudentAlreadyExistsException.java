package com.rgportfolio.demo.exceptions;

public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
