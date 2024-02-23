package com.example.demopayment.service.impl;

import com.example.demopayment.dto.request.CurrencyRequest;
import com.example.demopayment.dto.response.CurrencyResponse;
import com.example.demopayment.model.Currency;
import com.example.demopayment.repository.CurrencyRepository;
import com.example.demopayment.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;


    @Override
    public void addCurrency(CurrencyRequest currencyRequest) {
        Currency currencyEntity = Currency.builder()
                .symbol(currencyRequest.getSymbol())
                .build();

        currencyRepository.save(currencyEntity);
    }

    @Override
    public CurrencyResponse getCurrency(int id) {
        Currency currency = currencyRepository.getReferenceById(id);

        return CurrencyResponse.builder()
                .id(currency.getId())
                .symbol(currency.getSymbol())
                .build();
    }
}
