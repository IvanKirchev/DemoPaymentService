package com.example.demopayment.controller;

import com.example.demopayment.dto.request.RegisterUserRequest;
import com.example.demopayment.dto.request.WalletResponse;
import com.example.demopayment.dto.response.UserResponse;
import com.example.demopayment.service.UserService;
import com.example.demopayment.service.WalletService;
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
    public ResponseEntity<Void> registerUser(@RequestBody RegisterUserRequest request) {
        userService.registerUser(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {
        UserResponse user = userService.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/wallets")
    public ResponseEntity<List<WalletResponse>> getUserWallets(@PathVariable UUID userId) {
        List<WalletResponse> wallets = walletService.getUserWallets(userId);

        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
}