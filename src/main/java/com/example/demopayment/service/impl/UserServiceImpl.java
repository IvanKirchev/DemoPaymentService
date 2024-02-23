package com.example.demopayment.service.impl;

import com.example.demopayment.dto.request.RegisterUserRequest;
import com.example.demopayment.dto.response.UserResponse;
import com.example.demopayment.model.User;
import com.example.demopayment.repository.UserRepository;
import com.example.demopayment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(RegisterUserRequest user) {

        User newUser = User.builder()
                .age(user.getAge())
                .fullName(user.getName())
                .build();

        userRepository.save(newUser);
    }

    @Override
    public UserResponse getUser(UUID id) {
        User user = userRepository.getReferenceById(id);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getFullName())
                .age(user.getAge())
                .build();
    }
}
