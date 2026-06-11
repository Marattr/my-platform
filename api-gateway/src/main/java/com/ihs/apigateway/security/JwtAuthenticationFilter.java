package com.ihs.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    @Value("${app.x-api-key}")
    private String xApiKey;

    private static final List<String> WHITELIST = List.of(
            "/api/currency/hello",
            "/api/customer/hello2",
            "/api/auth/login",
            "/api/auth/refresh",

            "/api/order/saveOrder",
            "/api/customerproduct/**"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isWhitelisted(path)) {
            ServerHttpRequest cleaned = exchange.getRequest().mutate()
                    .headers(headers -> {
                        headers.remove("X-Auth-User-Id");
                        headers.remove("X-Auth-User-Roles");
                        headers.remove("X-Api-Key");
                    })
                    .build();
            return chain.filter(exchange.mutate().request(cleaned).build());
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(7);
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Object rawRoles = claims.get("roles");
            List<String> roles = rawRoles instanceof List<?> list ? list.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .toList() : List.of();
            String rolesHeader = String.join(",", roles);

            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .headers(headers -> {
                        headers.remove("X-Auth-User-Id");
                        headers.remove("X-Auth-User-Roles");
                        headers.remove("X-Api-Key");
                    })
                    .header("X-Auth-User-Id", claims.getSubject())
                    .header("X-Auth-User-Roles", rolesHeader)
                    .header("X-Api-Key", xApiKey)
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } catch (JwtException e) {
            return unauthorized(exchange);
        }
    }

    private boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::startsWith)
                || path.endsWith("/actuator/health");
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}