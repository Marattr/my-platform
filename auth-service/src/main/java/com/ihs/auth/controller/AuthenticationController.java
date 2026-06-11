package com.ihs.auth.controller;

import com.ihs.auth.dto.LoginRequest;
import com.ihs.auth.dto.Tokens;
import com.ihs.auth.service.AuthenticationService;
import com.ihs.auth.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("AuthenticateUser request:{}", loginRequest.getUsername());
        Tokens tokens = authenticationService.authenticateUser(loginRequest);
        if (tokens == null || tokens.isError()) {
            logger.info("AuthenticateUser customer user not found:{}", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid credentials"));
        }

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", tokens.getRefreshToken().getToken())
                .path("/api/auth")
                .maxAge(Duration.ofDays(1))
                .httpOnly(true)
                //.secure(true)
                //.sameSite("None")
                .build();

        logger.info("AuthenticateUser login successful:{}", loginRequest.getUsername());
        //auditLogService.create(customer.getId(), AuditLogType.LOGIN, "Sisteme giriş yapıldı");

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(new AuthResponse(tokens.getAccessToken(), "Bearer"));
    }
}
