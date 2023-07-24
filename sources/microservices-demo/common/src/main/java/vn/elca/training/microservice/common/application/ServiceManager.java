package vn.elca.training.microservice.common.application;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.api.ServiceDefinition;
import vn.elca.training.microservice.common.messaging.MessageClient;
import vn.elca.training.microservice.common.messaging.MessageListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServiceManager {
    private final ServiceDefinition serviceDefinition;
    private final MessageClient messageClient;
    private final MessageListener messageListener;

    public void start() {
        log.info("Service {} is starting", serviceDefinition.serviceName());
        serviceDefinition.start();
        messageClient.start();
        messageClient.subscribeToTopic(serviceDefinition.getInputQueue(), messageListener);
        log.info("Service {} is started", serviceDefinition.serviceName());
    }

    @PreDestroy
    public void stop() {
        log.info("Service {} is stopping", serviceDefinition.serviceName());
        messageClient.stop();
    }
}
