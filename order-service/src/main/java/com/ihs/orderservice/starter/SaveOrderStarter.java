package com.ihs.orderservice.starter;

import com.ihs.common.dto.SaveOrderRequest;
import com.ihs.orderservice.temporal.workflow.SaveOrderWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveOrderStarter {

    private final WorkflowClient workflowClient;

    public String start(SaveOrderRequest saveOrderRequest) {
        SaveOrderWorkflow workflow = workflowClient.newWorkflowStub(
                SaveOrderWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("order-task-queue")
                        .setWorkflowId("order-save-" + UUID.randomUUID())
                        .build()
        );
        return workflow.saveOrder(saveOrderRequest);
    }
}