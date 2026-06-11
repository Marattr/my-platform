package com.ihs.auth.dto;

import lombok.Data;

@Data
public class Tokens {
    private String accessToken;
    private RefreshToken refreshToken;
    private boolean error;
    private String errorText;
}
