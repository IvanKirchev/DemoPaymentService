package com.example.demopayment.service;

import com.example.demopayment.dto.request.DepositRequest;
import com.example.demopayment.dto.request.TransactionRequest;
import com.example.demopayment.dto.response.DepositResponse;
import com.example.demopayment.dto.response.TransactionResponse;
import com.example.demopayment.exceptions.CurrencyNotSupportedException;
import com.example.demopayment.exceptions.NotEnoughBalanceException;
import com.example.demopayment.exceptions.WalletNotFoundException;

import java.util.UUID;

public interface TransactionService {

    TransactionResponse transfer(TransactionRequest transactionRequest) throws NotEnoughBalanceException, WalletNotFoundException, CurrencyNotSupportedException;

    TransactionResponse deposit(DepositRequest depositRequest, UUID userId);
}
