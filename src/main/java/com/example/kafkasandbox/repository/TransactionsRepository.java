package com.example.kafkasandbox.repository;

import com.example.kafkasandbox.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, UUID> {
}
