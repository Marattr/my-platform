package com.ihs.orderservice.temporal.workflow;

import com.ihs.common.dto.SaveOrderRequest;
import com.ihs.common.temporal.CustomerProductActivities;
import com.ihs.common.temporal.SaveOrderActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

import java.time.Duration;

@WorkflowImpl(taskQueues = "order-task-queue")
public class SaveOrderWorkflowImpl implements SaveOrderWorkflow {

    private final SaveOrderActivities orderActivities = Workflow.newActivityStub(
            SaveOrderActivities.class,
            ActivityOptions.newBuilder()
                    .setTaskQueue("order-task-queue")
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .setRetryOptions(RetryOptions.newBuilder()
                            .setMaximumAttempts(3)
                            .build())
                    .build()
    );

    private final CustomerProductActivities productActivities = Workflow.newActivityStub(
            CustomerProductActivities.class,
            ActivityOptions.newBuilder()
                    .setTaskQueue("customer-product-task-queue")
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .setRetryOptions(RetryOptions.newBuilder()
                            .setMaximumAttempts(3)
                            .build())
                    .build()
    );

    @Override
    public String saveOrder(SaveOrderRequest saveOrderRequest) {
        Saga saga = new Saga(new Saga.Options.Builder()
                .setParallelCompensation(false)
                .build());

        try {
            String orderId = orderActivities.saveOrder(saveOrderRequest);
            saga.addCompensation(() -> orderActivities.cancelOrder(orderId));

            productActivities.saveCustomerProduct(orderId, saveOrderRequest.getCustomerId());
            saga.addCompensation(() -> productActivities.removeCustomerProducts(orderId));

        } catch (Exception e) {
            saga.compensate();
            throw e;
        }
        return "OK";
    }
}