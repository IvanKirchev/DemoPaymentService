package com.example.kafkasandbox.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
@Builder
public class TransactionDto {

    @Min(500)
    @NotNull
    private int amount;

    @NotNull
    private UUID senderId;

    @NotNull
    private UUID receiverId;

    @NotNull
    Integer currencyId;
}
