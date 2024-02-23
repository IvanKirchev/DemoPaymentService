package com.example.demopayment.repository;

import com.example.demopayment.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    List<Wallet> findByUserId(UUID userId);

    @Modifying
    @Query(
            """
                update Wallet w
                set w.balance = balance - :amount
                where
                    w.id = :walletId
            """
    )
    void subtractFromBalance(@Param("walletId") UUID walletId, @Param("amount") int amount);

    @Modifying
    @Query(
            """
                update Wallet w
                set w.balance = balance + :amount
                where
                    w.id = :walletId
            """
    )
    void addToBalance(@Param("walletId") UUID walletId, @Param("amount") int amount);

    @Query(
            """
                select w
                from Wallet w
                where
                    w.user.id = :userId and
                    w.currency.id = :currencyId
            """
    )
    Wallet getWalletByUserAndCurrency(@Param("userId") UUID userId, @Param("currencyId") int currencyId);
}
