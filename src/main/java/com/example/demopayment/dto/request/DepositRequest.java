package com.example.demopayment.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DepositRequest {

    @Min(500)
    private int amount;

    @NotNull
    private int currencyId;
}
