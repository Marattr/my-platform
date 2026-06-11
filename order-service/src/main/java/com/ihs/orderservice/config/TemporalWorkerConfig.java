package com.ihs.orderservice.config;

import com.ihs.orderservice.temporal.activity.SaveOrderActivitiesImpl;
import com.ihs.orderservice.temporal.workflow.SaveOrderWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TemporalWorkerConfig {

    private final SaveOrderActivitiesImpl saveOrderActivities;

    @Value("${spring.temporal.connection.target}")
    private String temporalTarget;

    @Value("${spring.temporal.namespace}")
    private String namespace;

    @Bean
    public WorkflowClient workflowClient() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(
                WorkflowServiceStubsOptions.newBuilder()
                        .setTarget(temporalTarget)
                        .build()
        );

        return WorkflowClient.newInstance(
                service,
                WorkflowClientOptions.newBuilder()
                        .setNamespace(namespace)
                        .build()
        );
    }

    @Bean(initMethod = "start")
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker("order-task-queue");
        worker.registerWorkflowImplementationTypes(SaveOrderWorkflowImpl.class);
        worker.registerActivitiesImplementations(saveOrderActivities);
        return factory;
    }
}
