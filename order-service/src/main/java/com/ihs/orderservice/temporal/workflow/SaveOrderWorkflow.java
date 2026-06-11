package com.ihs.orderservice.temporal.workflow;

import com.ihs.common.dto.SaveOrderRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SaveOrderWorkflow {

    @WorkflowMethod
    String saveOrder(SaveOrderRequest saveOrderRequest);
}