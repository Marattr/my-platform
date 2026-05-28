package com.ihs.customerservice.repo;

public class CustomerQueries {
    public static final String GET_CUSTOMER = "SELECT id,name,money FROM customer WHERE id = :id";
}
