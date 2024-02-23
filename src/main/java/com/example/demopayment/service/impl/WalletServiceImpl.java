package com.example.demopayment.service.impl;

import com.example.demopayment.dto.request.RegisterUserRequest;
import com.example.demopayment.dto.request.WalletResponse;
import com.example.demopayment.dto.response.CurrencyResponse;
import com.example.demopayment.model.Currency;
import com.example.demopayment.model.User;
import com.example.demopayment.model.Wallet;
import com.example.demopayment.repository.CurrencyRepository;
import com.example.demopayment.repository.UserRepository;
import com.example.demopayment.repository.WalletRepository;
import com.example.demopayment.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public UUID addWallet(UUID userId, int currencyId) {
        User user = userRepository.getReferenceById(userId);
        Currency currency = currencyRepository.getReferenceById(currencyId);

        log.info("Creating {} wallet for user {} with id {}", currency.getSymbol(), user.getFullName(), user.getId());

        Wallet wallet = new Wallet();
        wallet.setBalance(0);
        wallet.setCurrency(currency);
        wallet.setUser(user);

        Wallet newWallet = walletRepository.save(wallet);

        log.info("Wallet {} with id create for user {}", newWallet.getId(), user.getFullName());
        return newWallet.getId();
    }

    @Override
    public List<WalletResponse> getUserWallets(UUID userId) {
        List<Wallet> userWallets = walletRepository.findByUserId(userId);

        List<WalletResponse> wallets = new ArrayList<>();

        for (Wallet userWallet : userWallets) {
            RegisterUserRequest userDto = RegisterUserRequest.builder()
                    .id(userWallet.getUser().getId())
                    .age(userWallet.getUser().getAge())
                    .name(userWallet.getUser().getFullName()).build();

            CurrencyResponse currencyDto = CurrencyResponse.builder()
                    .id(userWallet.getCurrency().getId())
                    .symbol(userWallet.getCurrency().getSymbol())
                    .build();

            wallets.add(WalletResponse.builder()
                    .id(userWallet.getId())
                    .user(userDto)
                    .currency(currencyDto)
                    .balance(userWallet.getBalance())
                    .build());
        }

        return wallets;
    }

    @Override
    public WalletResponse getWallet(UUID walletId) {
        return null;
    }

    @Override
    public void removeWallet(UUID walletId) {

    }
}