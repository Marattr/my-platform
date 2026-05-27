package com.ihs.currencyservice.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {
    private long id;
    private String currency;
    private BigDecimal value;
}
