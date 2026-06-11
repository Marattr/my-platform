package com.ihs.customerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "currency-service", url = "${currency-service.url}", fallback = CurrencyClientFallback.class)
public interface CurrencyClient {

    @GetMapping("/api/currency/{currency}")
    BigDecimal getCurrency(@PathVariable String currency);

}
