package com.example.kafkasandbox.controller;

import com.example.kafkasandbox.dto.TransactionDto;
import com.example.kafkasandbox.dto.request.TransactionRequest;
import com.example.kafkasandbox.dto.response.TransactionResponse;
import com.example.kafkasandbox.exceptions.CurrencyNotSupportedException;
import com.example.kafkasandbox.exceptions.NotEnoughBalanceException;
import com.example.kafkasandbox.exceptions.WalletNotFoundException;
import com.example.kafkasandbox.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<TransactionResponse> makeTransaction(@Valid @RequestBody TransactionRequest request)
            throws NotEnoughBalanceException, WalletNotFoundException, CurrencyNotSupportedException {

        TransactionResponse transactionResponse = transactionService.transfer(request);

        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

}
