package com.example.kafkasandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseApiErrorResponse {
    private String message;
}
