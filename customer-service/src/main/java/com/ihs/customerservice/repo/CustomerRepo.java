package com.ihs.customerservice.repo;

import com.ihs.customerservice.domain.Customer;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerRepo implements CustomerRepoIntf {

    private Jdbi jdbi;

    public CustomerRepo(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Customer getCustomer(long id) {
        Optional<Customer> curr = jdbi.withHandle(handle ->
                handle.createQuery(CustomerQueries.GET_CUSTOMER)
                        .bind("id", id)
                        .mapToBean(Customer.class)
                        .findOne()
        );
        return curr.orElse(null);
    }
}
