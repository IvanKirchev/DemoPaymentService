package com.example.demopayment.service;

import com.example.demopayment.dto.request.TransactionRequest;
import com.example.demopayment.dto.response.TransactionResponse;
import com.example.demopayment.exceptions.CurrencyNotSupportedException;
import com.example.demopayment.exceptions.NotEnoughBalanceException;
import com.example.demopayment.exceptions.WalletNotFoundException;

public interface TransactionService {

    TransactionResponse transfer(TransactionRequest transactionRequest) throws NotEnoughBalanceException, WalletNotFoundException, CurrencyNotSupportedException;
}
