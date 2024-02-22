package com.example.kafkasandbox.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WalletDto {

    private UUID id;
    private int balance;
    private UserDto user;
    private CurrencyDto currency;
}
