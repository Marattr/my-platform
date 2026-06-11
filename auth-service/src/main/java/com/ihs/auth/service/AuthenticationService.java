package com.ihs.auth.service;

import com.ihs.auth.domain.Customer;
import com.ihs.auth.dto.LoginRequest;
import com.ihs.auth.dto.RefreshToken;
import com.ihs.auth.dto.Tokens;
import com.ihs.auth.feign.CustomerFeignClient;
import com.ihs.auth.util.JwtUtil;
import com.ihs.auth.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CustomerFeignClient customerFeignClient;
    private final JwtUtil jwtUtil;

    public Tokens authenticateUser(LoginRequest loginRequest){
        String name = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (StringUtil.isEmpty(name) && StringUtil.isEmpty(password)) {
            return null;
        }
        Customer customer = customerFeignClient.findByUsername(name);
        if(customer == null){
            return null;
        }
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if(!bcrypt.matches(password, customer.getPasswdHash())){
            return null;
        }

        List<String> roles = customerFeignClient.getCustomerRoles(customer.getId());
        String accessToken = jwtUtil.generateAccessToken(customer.getEmailAddress(), roles);
        //RefreshToken refreshToken = tokenService.createRefreshToken(customer.getEmailAddress());
        Tokens tokens = new Tokens();
        tokens.setAccessToken(accessToken);
        Instant expiryDate = Instant.now().plus(1, ChronoUnit.DAYS);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("TEST_TEST");
        refreshToken.setUsername(customer.getEmailAddress());
        refreshToken.setExpiryDate(expiryDate);
        tokens.setRefreshToken(refreshToken);
        return tokens;
    }

}
