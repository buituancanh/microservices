package vn.elca.tech.microservices.order.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import vn.elca.training.microservice.common.application.ApplicationConfiguration;

@SpringBootApplication
@Import({OrderConfiguration.class, ApplicationConfiguration.class})
@Slf4j
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
