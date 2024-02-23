package com.example.demopayment.controller;

import com.example.demopayment.dto.request.CreateWalletRequest;
import com.example.demopayment.dto.response.WalletResponse;
import com.example.demopayment.model.Wallet;
import com.example.demopayment.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<UUID> addWallet(@RequestBody CreateWalletRequest request) {
        UUID walletId = walletService.addWallet(request.getUserId(), request.getCurrencyId());

        return new ResponseEntity<>(walletId, HttpStatus.OK);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable UUID walletId) {
        return new ResponseEntity<>(walletService.getWallet(walletId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> getWalletsWithBalanceAbove(@RequestParam Integer minBalance) {
        return new ResponseEntity<>(walletService.getWalletsWithBalanceAbove(minBalance), HttpStatus.OK);
    }
}
