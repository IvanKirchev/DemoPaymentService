package com.example.demopayment.service;

import com.example.demopayment.dto.request.CurrencyRequest;
import com.example.demopayment.dto.response.CurrencyResponse;

public interface CurrencyService {

    void addCurrency(CurrencyRequest currency);

    CurrencyResponse getCurrency(int id);
}
