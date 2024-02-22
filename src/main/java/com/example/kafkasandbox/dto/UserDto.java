package com.example.kafkasandbox.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String name;
    private int age;
}
