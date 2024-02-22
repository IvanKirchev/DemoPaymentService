package com.example.kafkasandbox.service.impl;

import com.example.kafkasandbox.dto.CurrencyDto;
import com.example.kafkasandbox.dto.UserDto;
import com.example.kafkasandbox.dto.WalletDto;
import com.example.kafkasandbox.model.Currency;
import com.example.kafkasandbox.model.User;
import com.example.kafkasandbox.model.Wallet;
import com.example.kafkasandbox.repository.CurrencyRepository;
import com.example.kafkasandbox.repository.UserRepository;
import com.example.kafkasandbox.repository.WalletRepository;
import com.example.kafkasandbox.service.WalletService;
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
    public List<WalletDto> getUserWallets(UUID userId) {
        List<Wallet> userWallets = walletRepository.findByUserId(userId);

        List<WalletDto> wallets = new ArrayList<>();

        for (Wallet userWallet : userWallets) {
            UserDto userDto = UserDto.builder()
                    .id(userWallet.getUser().getId())
                    .age(userWallet.getUser().getAge())
                    .name(userWallet.getUser().getFullName()).build();

            CurrencyDto currencyDto = CurrencyDto.builder()
                    .id(userWallet.getCurrency().getId())
                    .symbol(userWallet.getCurrency().getSymbol())
                    .build();

            wallets.add(WalletDto.builder()
                    .id(userWallet.getId())
                    .user(userDto)
                    .currency(currencyDto)
                    .balance(userWallet.getBalance())
                    .build());
        }

        return wallets;
    }

    @Override
    public WalletDto getWallet(UUID walletId) {
        return null;
    }

    @Override
    public void removeWallet(UUID walletId) {

    }
}
