package com.example.kafkasandbox.service;

import com.example.kafkasandbox.dto.CurrencyDto;
import com.example.kafkasandbox.model.Currency;

public interface CurrencyService {

    void addCurrency(CurrencyDto currency);

    CurrencyDto getCurrency(int id);
}
