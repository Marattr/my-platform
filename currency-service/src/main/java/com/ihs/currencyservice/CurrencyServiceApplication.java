package com.ihs.currencyservice;

import com.ihs.common.config.ConfigProperties;
import com.ihs.common.config.FeignInternalInterceptor;
import com.ihs.common.security.GatewayAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(ConfigProperties.class)
@Import({GatewayAuthenticationFilter.class, FeignInternalInterceptor.class})
public class CurrencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyServiceApplication.class, args);
    }
}
