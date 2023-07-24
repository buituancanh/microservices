/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservice.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author tcb
 */

@SpringBootApplication
@Import(MailServiceConfiguration.class)
@Slf4j
public class MailServiceApplication {
    public static void main(String[] args) {
        log.info("Starting Mail Service server");
        SpringApplication.run(MailServiceApplication.class, args);
    }
}
