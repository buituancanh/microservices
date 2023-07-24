package vn.elca.tech.microservices.order.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vn.elca.training.microservice.common.api.ApiConfiguration;

import java.util.List;

@Configuration
@ComponentScan
@PropertySource("classpath:order-api.properties")
@RequiredArgsConstructor
public class OrderApiConfiguration implements ApiConfiguration {

    @Value("${order.command.queue}")
    private final String commandQueue;
    @Value("${order.event.topic}")
    private final String eventTopic;
    @Value("${order.service}")
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
        return List.of(vn.elca.training.microservices.order.dto.ObjectFactory.class.getPackage().getName());
    }
}
