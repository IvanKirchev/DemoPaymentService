package com.example.kafkasandbox.service;

import com.example.kafkasandbox.dto.request.TransactionRequest;
import com.example.kafkasandbox.dto.response.TransactionResponse;
import com.example.kafkasandbox.exceptions.CurrencyNotSupportedException;
import com.example.kafkasandbox.exceptions.NotEnoughBalanceException;
import com.example.kafkasandbox.exceptions.WalletNotFoundException;

import java.util.UUID;

public interface TransactionService {

    TransactionResponse transfer(TransactionRequest transactionRequest) throws NotEnoughBalanceException, WalletNotFoundException, CurrencyNotSupportedException;
}
