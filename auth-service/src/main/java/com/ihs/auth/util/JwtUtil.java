package com.ihs.auth.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String generateAccessToken(String username, List<String> roles) {
        Date currentDate = new Date();
        // 15 minutes (900000 ms)
        Date expiryDate = new Date(currentDate.getTime() + 900000);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .claim("type", "access")
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public boolean isImpersonated(String token) {
        Boolean impersonated = getClaims(token).get("isImpersonated", Boolean.class);
        return impersonated != null && impersonated;
    }

    public String getImpersonator(String token) {
        return getClaims(token).get("impersonatedBy", String.class);
    }

    private Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateRefreshToken(String userId) {
        Date now = new Date();
        // 1 Day
        Date expiryDate = new Date(now.getTime() + 86400000L);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(userId)
                .claim("type", "refresh")
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getTokenType(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("type",String.class);
    }

    private String getUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
   }


}
