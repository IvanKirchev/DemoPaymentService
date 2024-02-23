package com.example.demopayment.dto.request;

import com.example.demopayment.dto.response.CurrencyResponse;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WalletResponse {

    private UUID id;
    private int balance;
    private RegisterUserRequest user;
    private CurrencyResponse currency;
}
