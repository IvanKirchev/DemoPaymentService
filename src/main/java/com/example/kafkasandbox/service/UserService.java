package com.example.kafkasandbox.service;

import com.example.kafkasandbox.dto.UserDto;
import com.example.kafkasandbox.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface UserService {

    void registerUser(UserDto user);
    UserDto getUser(UUID id);


}
