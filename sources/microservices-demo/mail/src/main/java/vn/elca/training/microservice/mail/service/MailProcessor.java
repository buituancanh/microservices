/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservice.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author tcb
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();

    private final MailService mailService;

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("mail.cmd", Consumed.with(STRING_SERDE, STRING_SERDE));
        stream.groupByKey()
                .reduce((String value1, String value2) -> value1 + value2)
                .toStream()
                .map((key, value) -> {
                    mailService.sendMail(key, value);
                    return new KeyValue<>(key, String.format("Email was sent to %s", key));
                }).to("mail.evt");
    }
}
