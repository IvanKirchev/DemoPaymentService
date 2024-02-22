package com.example.kafkasandbox.dto;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorResponse {

    private final List<Violation> violations;

    public ValidationErrorResponse() {
        violations = new ArrayList<>();
    }
}

