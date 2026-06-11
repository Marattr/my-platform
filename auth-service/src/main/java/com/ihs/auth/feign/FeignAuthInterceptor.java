package com.ihs.auth.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Value("${app.x-api-key}")
    private String xApiKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header("X-Auth-User-Id", "internal-user");
        template.header("X-Auth-User-Roles", "INTERNAL");
        template.header("X-Api-Key", xApiKey);
    }
}
