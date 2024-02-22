package com.example.kafkasandbox.repository;

import com.example.kafkasandbox.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
