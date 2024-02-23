package com.example.demopayment.service;

import com.example.demopayment.dto.response.WalletResponse;
import com.example.demopayment.exceptions.OperationNotSupported;
import com.example.demopayment.model.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletService {

    UUID addWallet(UUID userId, int currencyId);

    List<WalletResponse> getUserWallets(UUID userId);

    WalletResponse getWallet(UUID walletId);

    void removeWallet(UUID walletId) throws OperationNotSupported;

    List<WalletResponse> getWalletsWithBalanceAbove(Integer minBalance);
}
