package com.ihs.customerservice.feign;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyClientFallback implements CurrencyClient{

    @Override
    public BigDecimal getCurrency(String currency) {
        return BigDecimal.ZERO;
    }
}
