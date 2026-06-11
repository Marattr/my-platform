package com.ihs.common.temporal;

import com.ihs.common.dto.SaveOrderRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SaveOrderActivities {

    @ActivityMethod
    String saveOrder(SaveOrderRequest request);

    @ActivityMethod
    void cancelOrder(String orderId);
}
