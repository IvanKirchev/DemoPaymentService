package com.example.demopayment.exceptions;

public class WalletNotFoundException extends BaseApiException {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
