package com.example.kafkasandbox.service;

import com.example.kafkasandbox.dto.WalletDto;

import java.util.List;
import java.util.UUID;

public interface WalletService {

    UUID addWallet(UUID userId, int currencyId);

    List<WalletDto> getUserWallets(UUID userId);

    WalletDto getWallet(UUID walletId);

    void removeWallet(UUID walletId);
}
