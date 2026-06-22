package com.rohit.productservice.exception;


public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}