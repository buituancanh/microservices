package vn.elca.training.microservices.account.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import vn.elca.training.microservice.common.application.ApplicationConfiguration;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link AccountConfiguration}. This is a deliberate separation of concerns.
 * <p>
 * This class declares no beans and current package contains no components for
 * ComponentScan to find.
 *
 * @author tcb
 */
@SpringBootApplication
@Import({AccountConfiguration.class, ApplicationConfiguration.class})
@Slf4j
public class AccountApplication {

    public static void main(String[] args) {
        log.info("Starting Account application server");
        SpringApplication.run(AccountApplication.class, args);
    }
}
