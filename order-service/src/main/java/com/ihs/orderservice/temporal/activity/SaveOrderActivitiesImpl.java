package com.ihs.orderservice.temporal.activity;

import com.ihs.common.dto.SaveOrderRequest;
import com.ihs.common.temporal.SaveOrderActivities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaveOrderActivitiesImpl implements SaveOrderActivities {

    private static final Logger log = LoggerFactory.getLogger(SaveOrderActivitiesImpl.class);

    @Override
    public String saveOrder(SaveOrderRequest request) {
        log.info("saveOrder");
        return "";
    }

    @Override
    public void cancelOrder(String orderId) {
        log.info("cancelOrder");
    }
}
