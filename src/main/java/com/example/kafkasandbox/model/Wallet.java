package com.example.kafkasandbox.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private int balance;

    @ManyToOne
    @JoinColumn(
            name = "user_id", nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "currency_id", nullable = false
    )
    private Currency currency;
}
