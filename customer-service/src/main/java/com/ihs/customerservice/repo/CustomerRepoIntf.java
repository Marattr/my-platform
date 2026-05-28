package com.ihs.customerservice.repo;

import com.ihs.customerservice.domain.Customer;

public interface CustomerRepoIntf {
    Customer getCustomer(long id);
}
