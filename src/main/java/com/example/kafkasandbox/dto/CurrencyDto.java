package com.example.kafkasandbox.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDto {
    private int id;
    private String symbol;
}
