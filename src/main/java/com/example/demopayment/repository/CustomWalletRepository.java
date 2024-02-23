package com.example.demopayment.repository;

import com.example.demopayment.dto.response.WalletResponse;
import com.example.demopayment.model.Wallet;

import java.util.stream.Stream;

public interface CustomWalletRepository {
    Stream<WalletResponse> getWalletsWithBalanceAbove(int minBalance);
}
