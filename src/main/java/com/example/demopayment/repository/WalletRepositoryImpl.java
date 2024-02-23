package com.example.demopayment.repository;

import com.example.demopayment.dto.response.CurrencyResponse;
import com.example.demopayment.dto.response.UserResponse;
import com.example.demopayment.dto.response.WalletResponse;
import com.example.demopayment.model.Wallet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class WalletRepositoryImpl implements CustomWalletRepository {

    private final EntityManager em;

    @Override
    public Stream<WalletResponse> getWalletsWithBalanceAbove(int minBalance) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Wallet> cq = cb.createQuery(Wallet.class);

        Root<Wallet> wallet = cq.from(Wallet.class);
        Predicate balancePredicate = cb.greaterThan(wallet.get("balance"), minBalance);

        cq.where(balancePredicate);

        TypedQuery<Wallet> tq = em.createQuery(cq);

        return tq.getResultStream().map(
                (wlt) -> WalletResponse.builder()
                        .id(wlt.getId())
                        .balance(wlt.getBalance())
                        .user(UserResponse.builder()
                                        .age(wlt.getUser().getAge())
                                        .id(wlt.getUser().getId())
                                        .build())
                        .currency(CurrencyResponse.builder()
                                .id(wlt.getCurrency().getId())
                                .symbol(wlt.getCurrency().getSymbol())
                                .build()
                        ).build()
        );

    }
}
