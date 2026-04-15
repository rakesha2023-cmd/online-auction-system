package com.auction.service;

public class BidValidationException extends RuntimeException {
    public BidValidationException(String message) {
        super(message);
    }
}
