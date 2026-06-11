package com.ihs.customerservice.controller;

import com.ihs.common.config.ConfigProperties;
import com.ihs.customerservice.domain.Customer;
import com.ihs.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ConfigProperties configProperties;

    @GetMapping("/hello2")
    public String hello2() {
        return configProperties.getGreeting();
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public Customer getCustomer(@PathVariable String name) {
        return customerService.getCustomer(name);
    }

    @GetMapping("/internal/{name}")
    @PreAuthorize("hasAnyRole('INTERNAL')")
    public Customer getCustomerInternal(@PathVariable String name) {
        return customerService.getCustomer(name);
    }

    @GetMapping("/internal/roles/{customerId}")
    @PreAuthorize("hasAnyRole('INTERNAL')")
    public List<String> getCustomerRoles(@PathVariable Long customerId) {
        return customerService.getCustomerRoles(customerId);
    }

    @GetMapping("/feignTest/{currency}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public BigDecimal feignTest(@PathVariable String currency) {
        return customerService.getTest(currency);
    }
}
