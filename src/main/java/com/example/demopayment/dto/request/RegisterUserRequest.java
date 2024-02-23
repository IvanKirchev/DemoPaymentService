package com.example.demopayment.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterUserRequest {
    private UUID id;
    private String name;
    private int age;
}
