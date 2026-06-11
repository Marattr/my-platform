package com.ihs.auth.feign;

import com.ihs.auth.domain.Customer;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class CustomerFeignClientFallback implements FallbackFactory<CustomerFeignClient> {

    private static final Logger log = LoggerFactory.getLogger(CustomerFeignClientFallback.class);

    @Override
    public CustomerFeignClient create(Throwable cause) {
        log.error("CustomerFeignClient fallback, cause: {}", cause.getMessage(), cause);
        return new CustomerFeignClient() {
            @Override
            public Customer findByUsername(String name) {
                return null;
            }
            @Override
            public List<String> getCustomerRoles(Long customerId) {
                return List.of();
            }
        };
    }
}
