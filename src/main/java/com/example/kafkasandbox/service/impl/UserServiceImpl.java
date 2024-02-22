package com.example.kafkasandbox.service.impl;

import com.example.kafkasandbox.dto.UserDto;
import com.example.kafkasandbox.model.User;
import com.example.kafkasandbox.repository.UserRepository;
import com.example.kafkasandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(UserDto user) {

        User newUser = User.builder()
                .age(user.getAge())
                .fullName(user.getName())
                .build();

        userRepository.save(newUser);
    }

    @Override
    public UserDto getUser(UUID id) {
        User user = userRepository.getReferenceById(id);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getFullName())
                .age(user.getAge())
                .build();
    }
}
