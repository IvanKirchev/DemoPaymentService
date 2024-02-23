package com.example.demopayment.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRequest{
    private int id;
    private String symbol;
}
