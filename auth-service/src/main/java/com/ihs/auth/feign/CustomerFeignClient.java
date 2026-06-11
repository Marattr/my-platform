package com.ihs.auth.feign;

import com.ihs.auth.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service", url = "${customer-service.url}", fallbackFactory = CustomerFeignClientFallback.class)
public interface CustomerFeignClient {

    @GetMapping("/api/customer/internal/{name}")
    Customer findByUsername(@PathVariable String name);

    @GetMapping("/api/customer/internal/roles/{customerId}")
    List<String> getCustomerRoles(@PathVariable Long customerId);
}