package com.ihs.customerservice.repo;

import com.ihs.customerservice.domain.Customer;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepo implements CustomerRepoIntf {

    private Jdbi jdbi;

    public CustomerRepo(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Customer getCustomer(long id) {
        Optional<Customer> customer = jdbi.withHandle(handle ->
                handle.createQuery(CustomerQueries.GET_CUSTOMER)
                        .bind("id", id)
                        .mapToBean(Customer.class)
                        .findOne()
        );
        return customer.orElse(null);
    }

    @Override
    public Customer getCustomerByName(String name) {
        Optional<Customer> customer = jdbi.withHandle(handle ->
                handle.createQuery(CustomerQueries.GET_CUSTOMER_BY_EMAIL)
                        .bind("emailAddress", name)
                        .mapToBean(Customer.class)
                        .findOne()
        );
        return customer.orElse(null);
    }

    @Override
    public List<String> getCustomerRoles(long customerId) {
        return jdbi.withHandle(handle ->
                handle.createQuery(CustomerQueries.GET_CUSTOMER_ROLES)
                        .bind("customerId", customerId)
                        .mapTo(String.class)
                        .list()
        );
    }
}
