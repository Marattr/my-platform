package com.ihs.currencyservice.controller;

import com.ihs.common.config.ConfigProperties;
import com.ihs.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    private final ConfigProperties configProperties;

    @GetMapping("/hello")
    public String hello() {
        return configProperties.getGreeting();
    }

    @GetMapping("/{currency}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public BigDecimal getCurrency(@PathVariable String currency){
        return currencyService.getCurrency(currency);
    }
}
