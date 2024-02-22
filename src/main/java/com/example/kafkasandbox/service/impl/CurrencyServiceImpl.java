package com.example.kafkasandbox.service.impl;

import com.example.kafkasandbox.dto.CurrencyDto;
import com.example.kafkasandbox.model.Currency;
import com.example.kafkasandbox.repository.CurrencyRepository;
import com.example.kafkasandbox.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;


    @Override
    public void addCurrency(CurrencyDto currency) {
        Currency currencyEntity = Currency.builder()
                .symbol(currency.getSymbol())
                .build();

        currencyRepository.save(currencyEntity);
    }

    @Override
    public CurrencyDto getCurrency(int id) {

        Currency currency = currencyRepository.getReferenceById(id);

        return CurrencyDto.builder()
                .id(currency.getId())
                .symbol(currency.getSymbol())
                .build();
    }
}
