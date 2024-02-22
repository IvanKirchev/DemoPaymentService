package com.example.kafkasandbox.concurrency;

import com.example.kafkasandbox.dto.request.TransactionRequest;
import com.example.kafkasandbox.model.Currency;
import com.example.kafkasandbox.model.Transaction;
import com.example.kafkasandbox.model.User;
import com.example.kafkasandbox.model.Wallet;
import com.example.kafkasandbox.repository.TransactionsRepository;
import com.example.kafkasandbox.repository.WalletRepository;
import com.example.kafkasandbox.service.TransactionService;
import com.example.kafkasandbox.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class TransactionServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionsRepository transactionsRepository;

    @InjectMocks
    private TransactionServiceImpl transferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConcurrentTransfer() throws InterruptedException {
        // Mock wallet and transaction data
        UUID senderId = UUID.randomUUID();
        UUID receiverId = UUID.randomUUID();
        int currency_id = 1;
        int initialBalance = 50;
        int transferAmount = 50;

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(transferAmount);
        transactionRequest.setReceiverId(receiverId);
        transactionRequest.setSenderId(senderId);
        transactionRequest.setCurrencyId(currency_id);

        Currency currency = new Currency();
        currency.setId(1);
        currency.setSymbol("USD");

        // Mock wallet for sender with initial balance
        Wallet senderWallet = new Wallet();
        senderWallet.setId(UUID.randomUUID());
        senderWallet.setUser(new User());
        senderWallet.setCurrency(currency);
        senderWallet.setBalance(initialBalance);
        when(walletRepository.getWalletByUserAndCurrency(senderId, currency_id)).thenReturn(senderWallet);

        doAnswer((invocation -> {
            senderWallet.setBalance(senderWallet.getBalance() - transferAmount);
            return null;
        })).when(walletRepository).subtractFromBalance(senderWallet.getId(), transferAmount);

        // Mock wallet for receiver
        Wallet receiverWallet = new Wallet();
        receiverWallet.setId(UUID.randomUUID());
        receiverWallet.setUser(new User());
        receiverWallet.setCurrency(currency);
        receiverWallet.setBalance(0);
        when(walletRepository.getWalletByUserAndCurrency(receiverId, currency_id)).thenReturn(receiverWallet);

        // Create CountDownLatch with a count of 2
        CountDownLatch latch = new CountDownLatch(2);

        // Simulate concurrent executions
        new Thread(() -> {
            try {
                latch.countDown();
                latch.await(); // Wait for other thread to start
                transferService.transfer(transactionRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                latch.countDown();
                latch.await(); // Wait for other thread to start
                transferService.transfer(transactionRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Wait for threads to finish
        Thread.sleep(2000);

        System.out.println("DEBUGGG");
        System.out.println(senderWallet.getBalance());
    }
}
