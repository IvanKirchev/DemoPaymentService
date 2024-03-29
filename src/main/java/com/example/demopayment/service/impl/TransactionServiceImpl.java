package com.example.demopayment.service.impl;

import com.example.demopayment.dto.request.DepositRequest;
import com.example.demopayment.dto.request.TransactionRequest;
import com.example.demopayment.dto.response.TransactionResponse;
import com.example.demopayment.exceptions.CurrencyNotSupportedException;
import com.example.demopayment.exceptions.NotEnoughBalanceException;
import com.example.demopayment.exceptions.WalletNotFoundException;
import com.example.demopayment.model.Currency;
import com.example.demopayment.model.Transaction;
import com.example.demopayment.model.User;
import com.example.demopayment.model.Wallet;
import com.example.demopayment.projection.TransactionPartiesOnly;
import com.example.demopayment.repository.CurrencyRepository;
import com.example.demopayment.repository.TransactionsRepository;
import com.example.demopayment.repository.UserRepository;
import com.example.demopayment.repository.WalletRepository;
import com.example.demopayment.service.TransactionService;
import com.example.demopayment.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final WalletRepository walletRepository;
    private final WalletService walletService;
    private final TransactionsRepository transactionsRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public TransactionResponse transfer(TransactionRequest transactionReq)
            throws NotEnoughBalanceException, WalletNotFoundException, CurrencyNotSupportedException {

        boolean currencyExists = currencyRepository.existsById(transactionReq.getCurrencyId());
        if(!currencyExists) {
            log.error("Currency with id {} is not supported", transactionReq.getCurrencyId());
            throw new CurrencyNotSupportedException(String.format("Currency with id %s is not supported",
                    transactionReq.getCurrencyId())
            );
        }

        Wallet senderWallet = walletRepository
                .getWalletByUserAndCurrency(transactionReq.getSenderId(), transactionReq.getCurrencyId());

        if( senderWallet == null) {
            log.error("User {} doesn't have a wallet for currency with id {}",
                    transactionReq.getSenderId(), transactionReq.getCurrencyId()
            );
            throw new WalletNotFoundException(
                    String.format("User %s doesn't have a wallet for currency with id %s",
                            transactionReq.getSenderId(), transactionReq.getCurrencyId())
            );
        }

        if(senderWallet.getBalance() < transactionReq.getAmount()) {
            log.error("User {} doesn't have enough balance in wallet {} to transfer {} {}",
                    senderWallet.getUser().getFullName(), senderWallet.getId(),
                    transactionReq.getAmount(), senderWallet.getCurrency().getSymbol()
            );
            throw new NotEnoughBalanceException(
                    String.format("User %s doesn't have enough balance in wallet %s to transfer %s %s",
                            senderWallet.getUser().getFullName(), senderWallet.getId(), transactionReq.getAmount(),
                            senderWallet.getCurrency().getSymbol())
            );
        }

        walletRepository.subtractFromBalance(senderWallet.getId(), transactionReq.getAmount());

        Wallet receiverWallet = walletRepository
                .getWalletByUserAndCurrency(transactionReq.getReceiverId(), transactionReq.getCurrencyId());

        if (receiverWallet == null) {
            log.info("Receiver {} didn't have {} wallet. Creating it.",
                    transactionReq.getReceiverId(), senderWallet.getCurrency().getSymbol()
            );

           UUID receiverWalletId = walletService
                   .addWallet(transactionReq.getReceiverId(), transactionReq.getCurrencyId());
           receiverWallet = walletRepository.getReferenceById(receiverWalletId);
        }

        walletRepository.addToBalance(receiverWallet.getId(), transactionReq.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionReq.getAmount());
        transaction.setCurrency(senderWallet.getCurrency());
        transaction.setSender(senderWallet.getUser());
        transaction.setReceiver(receiverWallet.getUser());

        Transaction persistedTransaction = transactionsRepository.save(transaction);

        return new TransactionResponse(persistedTransaction.getId());
    }

    @Override
    public TransactionResponse deposit(DepositRequest depositRequest, UUID userId) {
        Currency currency = currencyRepository.getReferenceById(depositRequest.getCurrencyId());
        User user = userRepository.getReferenceById(userId);
        Wallet userWallet = walletRepository.getWalletByUserAndCurrency(userId, depositRequest.getCurrencyId());

        walletRepository.addToBalance(userWallet.getId(), depositRequest.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(depositRequest.getAmount());
        transaction.setReceiver(user);
        transaction.setCurrency(currency);

        UUID transactionId = transactionsRepository.save(transaction).getId();

        return new TransactionResponse(transactionId);
    }

    @Override
    public TransactionPartiesOnly getTransactionParties(UUID transactionId) {
        return  transactionsRepository.findTransactionById(transactionId);
    }
}
