package com.example.coursesystem.exception;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException() {
        super("Email is not valid");
    }
}
