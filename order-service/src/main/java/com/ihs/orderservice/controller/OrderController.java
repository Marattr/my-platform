package com.ihs.orderservice.controller;

import com.ihs.common.dto.SaveOrderRequest;
import com.ihs.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/saveOrder")
    public String saveOrder(@RequestBody SaveOrderRequest saveOrderRequest) {
        return orderService.saveOrder(saveOrderRequest);
    }

}