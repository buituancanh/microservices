/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservice.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;
import vn.elca.training.microservice.common.kafka.KafkaConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

/**
 * @author tcb
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = MailServiceConfiguration.class)
@Import({KafkaConfiguration.class})
@EnableKafkaStreams
@Slf4j
public class MailServiceConfiguration {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfig(KafkaProperties kafkaProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "mail-services");
        props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public NewTopic inputTopic() {
        return TopicBuilder.name("mail.cmd")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
