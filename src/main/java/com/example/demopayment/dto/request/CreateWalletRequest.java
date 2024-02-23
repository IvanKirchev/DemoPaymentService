package com.example.demopayment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class CreateWalletRequest {
    private UUID userId;
    private int currencyId;
}
