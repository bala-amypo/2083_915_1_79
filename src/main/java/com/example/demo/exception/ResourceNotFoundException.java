package com.example.demo.exception;

// Custom exception used when a resource is not found
public class ResourceNotFoundException extends RuntimeException {

    // Constructor that accepts an error message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
