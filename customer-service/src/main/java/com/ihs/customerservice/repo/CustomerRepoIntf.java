package com.ihs.customerservice.repo;

import com.ihs.customerservice.domain.Customer;

import java.util.List;

public interface CustomerRepoIntf {
    Customer getCustomer(long id);
    Customer getCustomerByName(String name);
    List<String> getCustomerRoles(long customerId);
}
