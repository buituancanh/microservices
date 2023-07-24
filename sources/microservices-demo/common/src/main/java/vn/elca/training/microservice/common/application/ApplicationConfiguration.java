package vn.elca.training.microservice.common.application;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {
    /**
     * Application start up event: trigger start life cycle hook of service manager.
     *
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> readyEvent(ServiceManager serviceManager) {
        return event -> serviceManager.start();
    }
}
