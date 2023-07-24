package vn.elca.training.microservices.account.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.api.ServiceDefinition;
import vn.elca.training.microservices.account.api.AccountApiConfiguration;

@Component
@RequiredArgsConstructor
public class AccountServiceDefinition implements ServiceDefinition {
    private final AccountApiConfiguration accountApiConfiguration;

    @Override
    public String serviceName() {
        return accountApiConfiguration.getServiceName();
    }

    @Override
    public String getInputQueue() {
        return accountApiConfiguration.getInputQueue();
    }

    @Override
    public String getOutputTopic() {
        return accountApiConfiguration.getOutputTopic();
    }
}
