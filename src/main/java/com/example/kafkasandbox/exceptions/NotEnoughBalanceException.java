package com.example.kafkasandbox.exceptions;

public class NotEnoughBalanceException extends BaseApiException {
    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
