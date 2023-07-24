package vn.elca.training.microservice.common.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@ComponentScan
@RequiredArgsConstructor
@Profile("kafka")
@PropertySource("classpath:kafka.properties")
@EnableKafka
@EnableAutoConfiguration
public class KafkaConfiguration {
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(DefaultKafkaProducerFactory<String, String> defaultKafkaProducerFactory) {
        return new KafkaTemplate<>(defaultKafkaProducerFactory);
    }
}
