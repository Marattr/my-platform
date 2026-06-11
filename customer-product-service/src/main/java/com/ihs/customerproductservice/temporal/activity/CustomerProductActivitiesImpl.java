package com.ihs.customerproductservice.temporal.activity;

import com.ihs.common.temporal.CustomerProductActivities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerProductActivitiesImpl implements CustomerProductActivities {

    private static final Logger log = LoggerFactory.getLogger(CustomerProductActivitiesImpl.class);

    @Override
    public void saveCustomerProduct(String orderId, Long customerId) {
        log.info("saveCustomerProduct");
        throw new RuntimeException("cust err");
    }

    @Override
    public void removeCustomerProducts(String orderId) {
        log.info("removeCustomerProducts");
    }

}
