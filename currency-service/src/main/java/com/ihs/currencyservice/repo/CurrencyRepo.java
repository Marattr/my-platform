package com.ihs.currencyservice.repo;

import com.ihs.currencyservice.domain.Currency;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CurrencyRepo implements CurrencyRepoIntf{

    private Jdbi jdbi;

    public CurrencyRepo(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Currency getCurrency(String currency) {
        Optional<Currency> curr = jdbi.withHandle(handle ->
                handle.createQuery(CurrencyQueries.GET_CURRENCY)
                        .bind("currency", currency)
                        .mapToBean(Currency.class)
                        .findOne()
        );
        return curr.orElse(null);
    }
}
