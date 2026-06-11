package com.ihs.common.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class RefreshToken {
    private Long id;
    private String username;
    private String token;
    private Instant expiryDate;
    private boolean revoked = false;
}
