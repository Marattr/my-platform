package com.ihs.currencyservice.repo;

public class CurrencyQueries {
    public static final String GET_CURRENCY = "SELECT id,currency,value FROM currency WHERE currency = :currency";
}
