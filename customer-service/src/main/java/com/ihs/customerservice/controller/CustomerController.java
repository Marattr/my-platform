package com.ihs.customerservice.controller;

import com.ihs.customerservice.config.ConfigProperties;
import com.ihs.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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

    @GetMapping("/feignTest/{currency}")
    public BigDecimal feignTest(@PathVariable String currency) {
        return customerService.getTest(currency);
    }
}
