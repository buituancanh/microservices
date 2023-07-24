package vn.elca.training.microservices.account.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vn.elca.training.microservice.common.api.ApiConfiguration;
import vn.elca.training.microservices.account.dto.ObjectFactory;

import java.util.List;

@Configuration
@ComponentScan
@PropertySource("classpath:account-api.properties")
@RequiredArgsConstructor
public class AccountApiConfiguration implements ApiConfiguration {

    @Value("${account.command.queue}")
    private final String commandQueue;
    @Value("${account.event.topic}")
    private final String eventTopic;
    @Value("${account.service}")
    private final String serviceName;


    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getInputQueue() {
        return commandQueue;
    }

    @Override
    public String getOutputTopic() {
        return eventTopic;
    }

    @Override
    public List<String> supportedPackages() {
        return List.of(ObjectFactory.class.getPackage().getName());
    }
}
