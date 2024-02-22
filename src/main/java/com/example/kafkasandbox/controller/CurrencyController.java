package com.example.kafkasandbox.controller;

import com.example.kafkasandbox.dto.CurrencyDto;
import com.example.kafkasandbox.service.CurrencyService;
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
    public ResponseEntity<Void> addCurrency(@RequestBody CurrencyDto currency){
        currencyService.addCurrency(currency);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{currencyId}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable int currencyId){

        return new ResponseEntity<>(currencyService.getCurrency(currencyId), HttpStatus.OK);
    }

}
