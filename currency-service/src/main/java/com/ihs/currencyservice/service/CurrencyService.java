package com.ihs.currencyservice.service;

import com.ihs.currencyservice.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyService {

    private final CurrencyRepo currencyRepo;

    public CurrencyService(@Autowired CurrencyRepo currencyRepo){
        this.currencyRepo = currencyRepo;
    }

    public BigDecimal getCurrency(String currency){
        return currencyRepo.getCurrency(currency).getValue();
    }
}
