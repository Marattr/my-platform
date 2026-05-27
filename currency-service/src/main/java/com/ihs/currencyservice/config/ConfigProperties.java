package com.ihs.currencyservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Data
@ConfigurationProperties(prefix = "app")
public class ConfigProperties {
    private String greeting;
}
