package com.example.demopayment.exceptions;

public class NotEnoughBalanceException extends BaseApiException {
    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
