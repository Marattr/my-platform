package com.ihs.customerproductservice.service;

import com.ihs.common.dto.SaveOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProductService {

    public String saveOrder(SaveOrderRequest saveOrderRequest){

        return "OK";
    }
}