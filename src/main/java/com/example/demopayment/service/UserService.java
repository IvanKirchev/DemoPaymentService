package com.example.demopayment.service;

import com.example.demopayment.dto.request.RegisterUserRequest;
import com.example.demopayment.dto.response.UserResponse;
import java.util.UUID;

public interface UserService {

    void registerUser(RegisterUserRequest user);
    UserResponse getUser(UUID id);
}
