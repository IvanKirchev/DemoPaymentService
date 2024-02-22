package com.example.kafkasandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class NewWalletDto {
    private UUID userId;
    private int currencyId;
}
