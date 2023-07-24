package vn.elca.tech.microservices.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.elca.tech.microservices.order.api.OrderApiConfiguration;
import vn.elca.training.microservice.common.api.ServiceDefinition;

@Component
@RequiredArgsConstructor
public class OrderServiceDefinition implements ServiceDefinition {
    private final OrderApiConfiguration orderApiConfiguration;

    @Override
    public String serviceName() {
        return orderApiConfiguration.getServiceName();
    }

    @Override
    public String getInputQueue() {
        return orderApiConfiguration.getInputQueue();
    }

    @Override
    public String getOutputTopic() {
        return orderApiConfiguration.getOutputTopic();
    }
}
