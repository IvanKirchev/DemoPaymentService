package com.example.kafkasandbox.controller;

import com.example.kafkasandbox.dto.UserDto;
import com.example.kafkasandbox.dto.WalletDto;
import com.example.kafkasandbox.service.UserService;
import com.example.kafkasandbox.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    @PostMapping()
    public ResponseEntity<Void> registerUser(@RequestBody  UserDto userDto) {
        userService.registerUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {
        UserDto user = userService.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/wallets")
    public ResponseEntity<List<WalletDto>> getUserWallets(@PathVariable UUID userId) {
        List<WalletDto> wallets = walletService.getUserWallets(userId);

        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
}
