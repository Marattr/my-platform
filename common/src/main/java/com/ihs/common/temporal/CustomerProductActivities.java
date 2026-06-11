package com.ihs.common.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface CustomerProductActivities {

    @ActivityMethod
    void saveCustomerProduct(String orderId, Long customerId);

    @ActivityMethod
    void removeCustomerProducts(String orderId);
}
