package com.example.kafkasandbox.controller;

import com.example.kafkasandbox.dto.NewWalletDto;
import com.example.kafkasandbox.dto.WalletDto;
import com.example.kafkasandbox.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<UUID> addWallet(@RequestBody NewWalletDto newWalletDto) {
        UUID walletId = walletService.addWallet(newWalletDto.getUserId(), newWalletDto.getCurrencyId());

        return new ResponseEntity<>(walletId, HttpStatus.OK);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDto> getWallet(@PathVariable UUID walletId) {
        return new ResponseEntity<>(walletService.getWallet(walletId), HttpStatus.OK);
    }
}
