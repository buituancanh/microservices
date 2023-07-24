/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservices.account.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.elca.training.microservice.common.converter.MessageConverterConfiguration;
import vn.elca.training.microservice.common.kafka.KafkaConfiguration;
import vn.elca.training.microservices.account.api.AccountApiConfiguration;

/**
 * @author tcb
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = AccountConfiguration.class)
@Import({KafkaConfiguration.class, AccountApiConfiguration.class, MessageConverterConfiguration.class})
@Slf4j
public class AccountConfiguration {
}
