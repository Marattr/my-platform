package com.ihs.customerservice.service;

import com.ihs.customerservice.client.CurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CurrencyClient currencyClient;

    public BigDecimal getTest(String currency){
        return currencyClient.getCurrency(currency);
    }
}
