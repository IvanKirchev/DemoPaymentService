package com.example.demopayment.controller;

import com.example.demopayment.dto.request.CurrencyRequest;
import com.example.demopayment.dto.response.CurrencyResponse;
import com.example.demopayment.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<Void> addCurrency(@RequestBody CurrencyRequest currencyRequest){
        currencyService.addCurrency(currencyRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{currencyId}")
    public ResponseEntity<CurrencyResponse> getCurrency(@PathVariable int currencyId){

        return new ResponseEntity<>(currencyService.getCurrency(currencyId), HttpStatus.OK);
    }

}
