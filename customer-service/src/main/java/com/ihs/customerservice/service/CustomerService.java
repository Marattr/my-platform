package com.ihs.customerservice.service;

import com.ihs.customerservice.client.CurrencyClient;
import com.ihs.customerservice.domain.Customer;
import com.ihs.customerservice.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CurrencyClient currencyClient;
    private final CustomerRepo customerRepo;

    public BigDecimal getTest(String currency){
        BigDecimal currencyDec = currencyClient.getCurrency(currency);
        Customer customer = customerRepo.getCustomer(1L);
        return currencyDec.multiply(customer.getMoney()).setScale(2, RoundingMode.HALF_UP);
    }
}
