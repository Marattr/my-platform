package com.ihs.orderservice.service;

import com.ihs.common.dto.SaveOrderRequest;
import com.ihs.orderservice.starter.SaveOrderStarter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final SaveOrderStarter saveOrderStarter;

    public String saveOrder(SaveOrderRequest saveOrderRequest){
        return saveOrderStarter.start(saveOrderRequest);
    }
}
