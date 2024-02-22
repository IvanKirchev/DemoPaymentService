package com.example.kafkasandbox.exceptions;

public class CurrencyNotSupportedException extends BaseApiException{
    public CurrencyNotSupportedException(String message) {
        super(message);
    }
}
