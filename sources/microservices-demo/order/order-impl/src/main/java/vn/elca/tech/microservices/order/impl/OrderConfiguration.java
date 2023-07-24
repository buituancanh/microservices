package vn.elca.tech.microservices.order.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.elca.tech.microservices.order.api.OrderApiConfiguration;
import vn.elca.training.microservice.common.converter.MessageConverterConfiguration;
import vn.elca.training.microservice.common.kafka.KafkaConfiguration;
import vn.elca.training.microservices.account.api.AccountApiConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({KafkaConfiguration.class, OrderApiConfiguration.class, MessageConverterConfiguration.class, AccountApiConfiguration.class})
@Slf4j
public class OrderConfiguration {
}
