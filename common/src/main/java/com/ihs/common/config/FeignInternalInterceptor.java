package com.ihs.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignInternalInterceptor implements RequestInterceptor {

    @Value("${app.x-api-key}")
    private String xApiKey;

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String userId = request.getHeader("X-Auth-User-Id");
            String roles = request.getHeader("X-Auth-User-Roles");

            if (userId != null) template.header("X-Auth-User-Id", userId);
            if (roles != null) template.header("X-Auth-User-Roles", roles);
            template.header("X-Api-Key", xApiKey);
        }
    }

}