package com.example.demopayment.controller;

import com.example.demopayment.dto.request.TransactionRequest;
import com.example.demopayment.dto.response.TransactionResponse;
import com.example.demopayment.exceptions.CurrencyNotSupportedException;
import com.example.demopayment.exceptions.NotEnoughBalanceException;
import com.example.demopayment.exceptions.WalletNotFoundException;
import com.example.demopayment.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> makeTransaction(@Valid @RequestBody TransactionRequest request)
            throws NotEnoughBalanceException, WalletNotFoundException, CurrencyNotSupportedException {

        TransactionResponse transactionResponse = transactionService.transfer(request);

        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

}
