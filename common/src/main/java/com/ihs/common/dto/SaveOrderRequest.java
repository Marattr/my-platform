package com.ihs.common.dto;

import lombok.Data;

@Data
public class SaveOrderRequest {
    private Long productId;
    private Long customerId;
}
