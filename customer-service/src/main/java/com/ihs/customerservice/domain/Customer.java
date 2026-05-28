package com.ihs.customerservice.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Customer {
    private long id;
    private String name;
    private BigDecimal money;
}
