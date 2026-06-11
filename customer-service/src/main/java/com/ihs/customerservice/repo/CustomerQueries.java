package com.ihs.customerservice.repo;

public class CustomerQueries {
    public static final String GET_CUSTOMER = "SELECT * FROM customer WHERE id = :id";
    public static final String GET_CUSTOMER_BY_EMAIL = "SELECT * FROM customer WHERE email_address = :emailAddress";
    public static final String GET_CUSTOMER_ROLES = "SELECT rol.name FROM customer as cust INNER JOIN user_roles as ur on cust.id = ur.user_id " +
            "INNER JOIN roles as rol on ur.role_id = rol.id WHERE cust.id = :customerId";
}
