package com.example.demopayment.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "sender_id", nullable = true
    )
    private User sender;

    @ManyToOne
    @JoinColumn(
            name = "receiver_id", nullable = false
    )
    private User receiver;

    private int amount;

    @ManyToOne
    @JoinColumn(
            name = "currency_id", nullable = false
    )
    private Currency currency;
}
