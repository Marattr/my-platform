package com.ihs.customerservice.service;

import com.ihs.customerservice.feign.CurrencyClient;
import com.ihs.customerservice.domain.Customer;
import com.ihs.customerservice.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CurrencyClient currencyClient;
    private final CustomerRepo customerRepo;

    public BigDecimal getTest(String currency){
        BigDecimal currencyDec = currencyClient.getCurrency(currency);
        Customer customer = customerRepo.getCustomer(206069L);
        return currencyDec.multiply(new BigDecimal(customer.getCustomerCode())).setScale(2, RoundingMode.HALF_UP);
    }

    public Customer getCustomer(String name){
        return customerRepo.getCustomerByName(name);
    }

    public List<String> getCustomerRoles(Long customerId){
        return customerRepo.getCustomerRoles(customerId);
    }
}
