package com.example.kafkasandbox.exceptions;

public class WalletNotFoundException extends BaseApiException {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
