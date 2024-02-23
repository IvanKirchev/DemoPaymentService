package com.example.demopayment.service;

import com.example.demopayment.dto.request.DepositRequest;
import com.example.demopayment.dto.request.WalletResponse;
import com.example.demopayment.dto.response.DepositResponse;
import com.example.demopayment.exceptions.OperationNotSupported;

import java.util.List;
import java.util.UUID;

public interface WalletService {

    UUID addWallet(UUID userId, int currencyId);

    List<WalletResponse> getUserWallets(UUID userId);

    WalletResponse getWallet(UUID walletId);

    void removeWallet(UUID walletId) throws OperationNotSupported;
}
