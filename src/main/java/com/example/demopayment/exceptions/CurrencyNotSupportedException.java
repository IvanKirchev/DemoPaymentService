package com.example.demopayment.exceptions;

public class CurrencyNotSupportedException extends BaseApiException{
    public CurrencyNotSupportedException(String message) {
        super(message);
    }
}
