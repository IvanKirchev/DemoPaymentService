package com.example.demopayment.repository;

import com.example.demopayment.model.Transaction;
import com.example.demopayment.projection.TransactionPartiesOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, UUID> {
    TransactionPartiesOnly findTransactionById(UUID transactionId);
}
