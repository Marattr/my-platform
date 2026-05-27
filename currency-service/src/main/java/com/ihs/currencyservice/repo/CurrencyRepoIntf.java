package com.ihs.currencyservice.repo;

import com.ihs.currencyservice.domain.Currency;

public interface CurrencyRepoIntf {
    Currency getCurrency(String currency);
}
