package com.example.kafkasandbox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private UUID transactionId;
}
